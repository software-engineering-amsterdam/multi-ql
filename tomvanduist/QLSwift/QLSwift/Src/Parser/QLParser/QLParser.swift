//
//  QLParser.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation
import SwiftParsec





/**
 * Grammer:
 *
 * form         ::= 'form' block
 * block        ::= { stmt* }
 * stmt         ::= 'if' ( expr) { block } | question
 * question     ::= var ':' stringLit expr
 * expr         ::= money | prefix expr | expr infix expr | ( expr ) | boolean | literal | var
 * literal      ::= true | false | stringLit | numberLit
 * prefix       ::= - | !
 * infix        ::= + | - | * | / | ^ | || | && | < | <= | == | >= | >
 * var          ::= identifier
 * money        ::= 'money' || 'money' ( expr )
 */
class QLParser: NSObject {
    
    private lazy var lexer = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
    private lazy var symbol: String -> GenericParser<String, (), String> = self.lexer.symbol
    
    
    /**
     * * form       ::= 'form' block
     */
    private lazy var form: GenericParser<String, (), QLForm> = {
        self.symbol("form") *> self.variable.flatMap { fVar in
            return self.block.map { fBlock in QLForm(variable: fVar, block: fBlock) }
        }
    }()
    
    
    /**
     * * block      ::= { stmt* }
     */
    private lazy var block: GenericParser<String, (), QLBlockStatement>! = {
        self.lexer.braces(
            self.stmt.manyAccumulator { acc, stmts in
                var tmp = stmts
                tmp.append(acc)
                return tmp
            }
        ).map { stmts in QLBlockStatement(block: stmts) }
    }()
    
    
    /**
     * stmt         ::= 'if' ( expr) { block } | question
     */
    private lazy var stmt: GenericParser<String, (), QLStatement> = {
        let qStmt: GenericParser<String, (), QLStatement> =
            self.question.map { sQuestion in QLQuestionStatement(question: sQuestion) }
        let ifStmt: GenericParser<String, (), QLStatement> =
            self.lexer.symbol("if") *> self.lexer.parentheses(self.expr).flatMap { cond in
                self.block.map { blockStmt in
                    QLIf(conditional: cond, block: blockStmt)
                }
            }
        
        return ifStmt <|> qStmt
    }()
    
    
    
    
    
    /**
     * * question   ::= var ':' string expr
     */
    private lazy var question: GenericParser<String, (), QLQuestion> = {
        self.variable.flatMap { qVar in
            self.lexer.colon *> self.string.flatMap { qLit in
                self.expr.map { qExpr in
                    QLQuestion(variable: qVar, stringLit: qLit as! QLStringLiteral, expression: qExpr)
                }
            }
        }
    }()
    
    
    /**
     * * expr       ::= money | prefix expr | expr infix expr | ( expr ) | boolField | literal | var
     */
    private lazy var expr: GenericParser<String, (), QLExpression> = {
        GenericParser.recursive {
        (_expr: GenericParser<String, (), QLExpression>) in
        
        let precExpr: GenericParser<String, (), QLExpression> =
            self.lexer.parentheses(_expr)
        let litExpr: GenericParser<String, (), QLExpression> =
            (self.bool <|> self.string <|> self.number).map { literal in
                QLExpressionLiteral(literal: literal)
            }
        let varExpr: GenericParser<String, (), QLExpression> =
            self.variable.map { eVar in QLExpressionVariable(variable: eVar) }
        let boolExpr: GenericParser<String, (), QLExpression> =
            self.lexer.symbol("boolean").map { _ in QLBoolean() }
        let prefix: GenericParser<String, (), QLPrefix.Type> =
            StringParser.character("-").map { _ in QLNeg.self } <|>
                StringParser.character("!").map { _ in QLNot.self }
        let prefixExpr: GenericParser<String, (), QLExpression> =
            prefix.flatMap { ePrefix in
                _expr.map { rhs in ePrefix.init(rhs: rhs) }
            }
        let infix: GenericParser<String, (), QLInfix.Type> =
            self.self.lexer.symbol("+").map { _ in QLAdd.self } <|>
                self.lexer.symbol("-").map { _ in QLSub.self } <|>
                self.lexer.symbol("*").map { _ in QLMul.self } <|>
                self.lexer.symbol("/").map { _ in QLDiv.self } <|>
                self.lexer.symbol("^").map { _ in QLPow.self } <|>
                self.lexer.symbol("&&").map { _ in QLAnd.self } <|>
                self.lexer.symbol("||").map { _ in QLOr.self } <|>
                self.lexer.symbol("==").map { _ in QLEq.self } <|>
                self.lexer.symbol("<=").map { _ in QLLe.self }.attempt <|>
                self.lexer.symbol(">=").map { _ in QLGe.self }.attempt <|>
                self.lexer.symbol("<").map { _ in QLLt.self } <|>
                self.lexer.symbol(">").map { _ in QLGt.self }
        
        // Left associative infix, TODO: remove lhs constraint on varExpr
        func opParser(lhs: QLExpression) -> GenericParser<String, (), QLExpression> {
            return infix.flatMap { eInfix in
                _expr.flatMap { rhs in
                    opParser1(eInfix.init(lhs: lhs, rhs: rhs))
                }
            }
        }
        func opParser1(rhs: QLExpression) -> GenericParser<String, (), QLExpression> {
            return opParser(rhs) <|> GenericParser(result: rhs)
        }
        let infixExpr: GenericParser<String, (), QLExpression> =
            (self.money <|> prefixExpr <|> precExpr <|> boolExpr <|> litExpr <|> varExpr).flatMap { lhs in
                opParser(lhs) <|> GenericParser(result: lhs)
            }
        
        
        return self.money <|> prefixExpr <|> infixExpr.attempt <|> precExpr <|> boolExpr <|> litExpr <|> varExpr
    }
    }()
    
    /**
     * * literal    ::= bool | string | number
     * * bool       ::= 'true' | 'false'
     * * string     ::= string
     * * number     ::= integer | float
     */
    private lazy var literal: GenericParser<String, (), QLLiteral> =
        self.bool <|> self.string <|> self.number
    private lazy var bool: GenericParser<String, (), QLLiteral> =
        self.lexer.symbol("true") *> GenericParser(result: QLBooleanLiteral(bool: true)) <|>
        self.lexer.symbol("false") *> GenericParser(result: QLBooleanLiteral(bool: false))
    private lazy var string: GenericParser<String, (), QLLiteral> =
        self.lexer.stringLiteral.map{ s in QLStringLiteral(string: s) }
    private lazy var int: GenericParser<String, (), QLLiteral> =
        self.lexer.integer.map { i in QLIntegerLiteral(integer: i) }
    private lazy var float: GenericParser<String, (), QLLiteral> =
        self.lexer.float.map { f in QLFloatLiteral(float: f) }
    private lazy var number: GenericParser<String, (), QLLiteral> =
        self.float.attempt <|> self.int
    
    
    /**
     * * money      ::= 'money' || 'money' ( expr )
     */
    private lazy var money: GenericParser<String, (), QLExpression> = {
        (self.lexer.symbol("money") *> self.lexer.parentheses(self.expr).map { e in QLMoney(expr: e) }).attempt
            <|>
        self.lexer.symbol("money").map { _ in QLMoney() }
    }()
    
    
    /**
     * * variable   ::= identifier
     */
    private lazy var variable: GenericParser<String, (), QLVariable>
        = self.lexer.identifier.map { id in QLVariable(identifier: id) }
    
    
//    override init() {
//        self.lexer = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
//    }
//    
    
    func parse(ql: QL) throws -> QLForm {
        return try qlParser().run(sourceName: "QL", input: ql)
    }
    
    private func qlParser() -> GenericParser<String, (), QLForm> {
        let lexer = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
        
        
        let variable = lexer.identifier.map { id in QLVariable(identifier: id) }
        
        let boolLit: GenericParser<String, (), QLLiteral> =
            lexer.symbol("true") *> GenericParser(result: QLBooleanLiteral(bool: true)) <|>
            lexer.symbol("false") *> GenericParser(result: QLBooleanLiteral(bool: false))
        let stringLit: GenericParser<String, (), QLLiteral> =
            lexer.stringLiteral.map{ s in QLStringLiteral(string: s) }
        let intLit: GenericParser<String, (), QLLiteral> =
            lexer.integer.map { i in QLIntegerLiteral(integer: i) }
        let floatLit: GenericParser<String, (), QLLiteral> =
            lexer.float.map { f in QLFloatLiteral(float: f) }
        let number: GenericParser<String, (), QLLiteral> =
            floatLit.attempt <|> intLit
        
        
        let expr: GenericParser<String, (), QLExpression> = GenericParser.recursive {
            (expr: GenericParser<String, (), QLExpression>) in
            
            let precExpr: GenericParser<String, (), QLExpression> =
                lexer.parentheses(expr)
            let litExpr: GenericParser<String, (), QLExpression> =
                (boolLit <|> stringLit <|> number).map { literal in
                    QLExpressionLiteral(literal: literal)
                }
            let varExpr: GenericParser<String, (), QLExpression> =
                variable.map { eVar in QLExpressionVariable(variable: eVar) }
            let boolExpr: GenericParser<String, (), QLExpression> =
                lexer.symbol("boolean").map { _ in QLBoolean() }
            let moneyExpr: GenericParser<String, (), QLExpression> =
                (lexer.symbol("money") *> lexer.parentheses(expr).map { e in QLMoney(expr: e) }).attempt <|>
                lexer.symbol("money").map { _ in QLMoney() }
            let prefix: GenericParser<String, (), QLPrefix.Type> =
                StringParser.character("-").map { _ in QLNeg.self } <|>
                StringParser.character("!").map { _ in QLNot.self }
            let prefixExpr: GenericParser<String, (), QLExpression> =
                prefix.flatMap { ePrefix in
                    expr.map { rhs in ePrefix.init(rhs: rhs) }
                }
            let infix: GenericParser<String, (), QLInfix.Type> =
                lexer.symbol("+").map { _ in QLAdd.self } <|>
                lexer.symbol("-").map { _ in QLSub.self } <|>
                lexer.symbol("*").map { _ in QLMul.self } <|>
                lexer.symbol("/").map { _ in QLDiv.self } <|>
                lexer.symbol("^").map { _ in QLPow.self } <|>
                lexer.symbol("&&").map { _ in QLAnd.self } <|>
                lexer.symbol("||").map { _ in QLOr.self } <|>
                lexer.symbol("==").map { _ in QLEq.self } <|>
                lexer.symbol("<=").map { _ in QLLe.self }.attempt <|>
                lexer.symbol(">=").map { _ in QLGe.self }.attempt <|>
                lexer.symbol("<").map { _ in QLLt.self } <|>
                lexer.symbol(">").map { _ in QLGt.self }

            // Left associative infix, TODO: remove lhs constraint on varExpr
            func opParser(lhs: QLExpression) -> GenericParser<String, (), QLExpression> {
                return infix.flatMap { eInfix in
                    expr.flatMap { rhs in
                        opParser1(eInfix.init(lhs: lhs, rhs: rhs))
                    }
                }
            }
            func opParser1(rhs: QLExpression) -> GenericParser<String, (), QLExpression> {
                return opParser(rhs) <|> GenericParser(result: rhs)
            }
            let infixExpr: GenericParser<String, (), QLExpression> =
                (moneyExpr <|> prefixExpr <|> precExpr <|> boolExpr <|> litExpr <|> varExpr).flatMap { lhs in
                    opParser(lhs) <|> GenericParser(result: lhs)
                }
        
            
            return moneyExpr <|> prefixExpr <|> infixExpr.attempt <|> precExpr <|> boolExpr <|> litExpr <|> varExpr
        }
        
        let question: GenericParser<String, (), QLQuestion> =
            variable.flatMap { qVar in
                lexer.colon *> stringLit.flatMap { qLit in
                    expr.map { qExpr in
                        QLQuestion(variable: qVar, stringLit: qLit as! QLStringLiteral, expression: qExpr)
                    }
                }
            }
        
        
        // This declaration is needed to define statements with blocks such as if statements
        var block: GenericParser<String, (), QLBlockStatement>!
        
        
        let qStmt: GenericParser<String, (), QLStatement> =
            question.map { sQuestion in QLQuestionStatement(question: sQuestion) }
        let ifStmt: GenericParser<String, (), QLStatement> =
            lexer.symbol("if") *> lexer.parentheses(expr).flatMap { cond in
                block.map { blockStmt in
                    QLIf(conditional: cond, block: blockStmt)
                }
            }
        let stmt = ifStmt <|> qStmt
        
        
        // Now define blocks as a list of statements
        block = lexer.braces(
            stmt.manyAccumulator { acc, stmts in
                var tmp = stmts
                tmp.append(acc)
                return tmp
            }
        ).map { stmts in QLBlockStatement(block: stmts) }
        
        let form: GenericParser<String, (), QLForm> =
            symbol("form") *> variable.flatMap { fVar in
                return block.map { fBlock in QLForm(variable: fVar, block: fBlock) }
            }
        
        
        return lexer.whiteSpace *> self.form <* StringParser.eof
    }
}