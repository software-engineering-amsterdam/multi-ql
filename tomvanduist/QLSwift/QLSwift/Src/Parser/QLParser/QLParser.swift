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
 * expr         ::= money | unaryOp expr | expr binary expr | ( expr ) | boolean | literal | var
 * literal      ::= true | false | stringLit | numberLit
 * unaryOp      ::= - | !
 * binary       ::= + | - | * | / | ^ | || | && | < | <= | == | >= | >
 * var          ::= identifier
 * money        ::= 'money' || 'money' ( expr )
 */
class QLParser: NSObject {
    
    func parse(ql: QL) throws -> Form {
        return try qlParser().run(sourceName: "QL", input: ql)
    }
    
    private func qlParser() -> GenericParser<String, (), Form> {
        let lexer   = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
        
        
        let identifier: GenericParser<String, (), Expression> =
            lexer.identifier.map { id in Identifier(id: id) }
        
        let boolLit: GenericParser<String, (), Expression> =
            lexer.symbol("true") *> GenericParser(result: BooleanLiteral(bool: true)) <|>
            lexer.symbol("false") *> GenericParser(result: BooleanLiteral(bool: false))
        let stringLit: GenericParser<String, (), Expression> =
            lexer.stringLiteral.map{ s in StringLiteral(string: s) }
        let intLit: GenericParser<String, (), Expression> =
            lexer.integer.map { i in IntegerLiteral(integer: i) }
        let floatLit: GenericParser<String, (), Expression> =
            lexer.float.map { f in FloatLiteral(float: f) }
        let number: GenericParser<String, (), Expression> =
            floatLit.attempt <|> intLit
        
        
        let expr: GenericParser<String, (), Expression> = GenericParser.recursive {
            (expr: GenericParser<String, (), Expression>) in
            
            let precExpr: GenericParser<String, (), Expression> =
                lexer.parentheses(expr)
            let litExpr: GenericParser<String, (), Expression> =
                boolLit <|> stringLit <|> number
//            let varExpr: GenericParser<String, (), Expression> =
//                variable.map { eVar in QLExpressionVariable(variable: eVar) }
            let boolExpr: GenericParser<String, (), Expression> =
                lexer.symbol("boolean").map { _ in BooleanField() }
            let stringExpr: GenericParser<String, (), Expression> =
                lexer.symbol("string").map { _ in StringField() }
            let moneyExpr: GenericParser<String, (), Expression> =
                (lexer.symbol("money") *> lexer.parentheses(expr).map { e in MoneyField(expression: e) }).attempt <|>
                lexer.symbol("money").map { _ in MoneyField() }
            let unary: GenericParser<String, (), Unary.Type> =
                StringParser.character("-").map { _ in Neg.self } <|>
                StringParser.character("!").map { _ in Not.self }
            let unaryExpr: GenericParser<String, (), Expression> =
                unary.flatMap { eUnary in
                    expr.map { rhs in eUnary.init(rhs: rhs) as! Expression }
                }
            let binary: GenericParser<String, (), Binary.Type> =
                lexer.symbol("+").map { _ in Add.self } <|>
                lexer.symbol("-").map { _ in Sub.self } <|>
                lexer.symbol("*").map { _ in Mul.self } <|>
                lexer.symbol("/").map { _ in Div.self } <|>
                lexer.symbol("^").map { _ in Pow.self } <|>
                lexer.symbol("&&").map { _ in And.self } <|>
                lexer.symbol("||").map { _ in Or.self } <|>
                lexer.symbol("==").map { _ in Eq.self } <|>
                lexer.symbol("!=").map { _ in Ne.self } <|>
                lexer.symbol("<=").map { _ in Le.self }.attempt <|>
                lexer.symbol(">=").map { _ in Ge.self }.attempt <|>
                lexer.symbol("<").map { _ in Lt.self } <|>
                lexer.symbol(">").map { _ in Gt.self }

            
            // Left associative binary, TODO: properly define lhs
            func opParser(lhs: Expression) -> GenericParser<String, (), Expression> {
                return binary.flatMap { eBinary in
                    expr.flatMap { rhs in
                        opParser1(eBinary.init(lhs: lhs, rhs: rhs) as! Expression)
                    }
                }
            }
            func opParser1(rhs: Expression) -> GenericParser<String, (), Expression> {
                return opParser(rhs) <|> GenericParser(result: rhs)
            }
            let binaryExpr =
                (moneyExpr <|> unaryExpr <|> precExpr <|> boolExpr <|> stringExpr <|> litExpr <|> identifier).flatMap { lhs in
                    opParser(lhs) <|> GenericParser(result: lhs)
                }
        
            
            return moneyExpr <|> unaryExpr <|> binaryExpr.attempt <|> precExpr <|> boolExpr <|> stringExpr <|> litExpr <|> identifier
        }
        
        let question: GenericParser<String, (), Statement> =
            identifier.flatMap { qId in
                lexer.colon *> lexer.stringLiteral.flatMap { qLit in
                    expr.map { qExpr in
                        Question(identifier: qId as! Identifier, label: qLit, expression: qExpr)
                    }
                }
            }
        
        
        // This declaration is needed to define statements with blocks such as if statements
        var block: GenericParser<String, (), Block>!
        
        
//        let qStmt: GenericParser<String, (), Statement> =
//            question.map { sQuestion in QuestionStatement(question: sQuestion) }
        let ifStmt: GenericParser<String, (), Statement> =
            lexer.symbol("if") *> lexer.parentheses(expr).flatMap { cond in
                block.map { blockStmt in
                    Conditional(condition: cond, ifBlock: blockStmt)
                }
            }
        let stmt: GenericParser<String, (), Statement> =
            ifStmt <|> question
        
        
        // Now define blocks as a list of statements
        block = lexer.braces(
            stmt.manyAccumulator { acc, stmts in
                var tmp = stmts
                tmp.append(acc)
                return tmp
            }
        ).map { stmts in Block(block: stmts) }
        
        let form: GenericParser<String, (), Form> =
            lexer.symbol("form") *> identifier.flatMap { fId in
                return block.map { fBlock in Form(identifier: fId as! Identifier, block: fBlock) }
            }
        
        
        return lexer.whiteSpace *> form <* StringParser.eof
    }
}