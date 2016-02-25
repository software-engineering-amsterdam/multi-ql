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
    
    let lexer   = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
    
    
    internal func parse(ql: QL) throws -> Form {
        return try (lexer.whiteSpace *> form() <* StringParser.eof).run(sourceName: "QL", input: ql)
    }
    
    private func qlParser() -> GenericParser<String, (), Form> {
        
        
        
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
        
        
        let _expr: GenericParser<String, (), Expression> = GenericParser.recursive {
            (expr: GenericParser<String, (), Expression>) in
            
            let precExpr: GenericParser<String, (), Expression> =
                lexer.parentheses(expr)
            let litExpr: GenericParser<String, (), Expression> =
                boolLit <|> stringLit <|> number
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
                lexer.symbol("==").map { _ in Eq.self } <|>
                lexer.symbol("!=").map { _ in Ne.self } <|>
                lexer.symbol("<=").map { _ in Le.self }.attempt <|>
                lexer.symbol(">=").map { _ in Ge.self }.attempt <|>
                lexer.symbol("<").map { _ in Lt.self } <|>
                lexer.symbol(">").map { _ in Gt.self } <|>
                lexer.symbol("&&").map { _ in And.self } <|>
                lexer.symbol("||").map { _ in Or.self }
            
            
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
            
//            return moneyExpr <|> unaryExpr <|> binaryExpr.attempt <|> precExpr <|> boolExpr <|> stringExpr <|> litExpr <|> identifier
            return moneyExpr <|> precExpr <|> boolExpr <|> stringExpr <|> litExpr <|> identifier
        }
        
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        
        let expr: GenericParser<String, (), Expression> =
            opTable().expressionParser { e in
                e.between(openingParen, closingParen) <|> _expr
            }
        
        let question: GenericParser<String, (), Statement> =
            identifier.flatMap { [unowned self] qId in
                self.lexer.colon *> self.lexer.stringLiteral.flatMap { qLit in
                    expr.map { qExpr in
                        Question(identifier: qId as! Identifier, label: qLit, expression: qExpr)
                    }
                }
            }
        
        
        // This declaration is needed to define statements with blocks such as if statements
        var block: GenericParser<String, (), Block>!
        
        
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
    
    internal func expr() -> GenericParser<String, (), Expression> {
        
        
        let expr: GenericParser<String, (), Expression> = GenericParser.recursive {
            (expr: GenericParser<String, (), Expression>) in
            
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
            let precExpr: GenericParser<String, (), Expression> =
                lexer.parentheses(expr)
            let litExpr: GenericParser<String, (), Expression> =
                boolLit <|> stringLit <|> number
            let boolExpr: GenericParser<String, (), Expression> =
                lexer.symbol("boolean").map { _ in BooleanField() }
            let stringExpr: GenericParser<String, (), Expression> =
                lexer.symbol("string").map { _ in StringField() }
            let moneyExpr: GenericParser<String, (), Expression> =
//                (lexer.symbol("money") *> lexer.parentheses(expr).map { e in MoneyField(expression: e) }).attempt <|>
                lexer.symbol("money").map { _ in MoneyField() }
            
            return moneyExpr <|> boolExpr <|> stringExpr <|> litExpr <|> identifierExpr()
        }
        
        let precExpr: GenericParser<String, (), Expression> =
            lexer.parentheses(expr)
        
        
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        let _expr: GenericParser<String, (), Expression> =
            opTable().expressionParser { e in
                (e.between(openingParen, closingParen) <* lexer.whiteSpace) <|> expr <|> precExpr
            }
        let _expr0: GenericParser<String, (), Expression> =
            opTable0().expressionParser { e in
                (e.between(openingParen, closingParen) <* lexer.whiteSpace) <|> expr <|> precExpr
            }
        
        
        let calcMoneyExpr: GenericParser<String, (), Expression> =
            lexer.symbol("money") *> lexer.parentheses(_expr).map { e in MoneyField(expression: e) }
        
        return lexer.whiteSpace *> (calcMoneyExpr.attempt <|> _expr.attempt <|> _expr0)
    }
    
    
    
    internal func question() -> GenericParser<String, (), Statement> {
        return identifier().flatMap { [unowned self] qId in
                self.lexer.colon *> self.lexer.stringLiteral.flatMap { [unowned self] qLit in
                    self.expr().map { qExpr in
                        Question(identifier: qId, label: qLit, expression: qExpr)
                    }
                }
            }
    }
    
    internal func ifStatement() -> GenericParser<String, (), Statement> {
        return
            lexer.symbol("if") *> lexer.parentheses(expr()).flatMap { [unowned self] cond in
                self.block().map { blockStmt in
                    Conditional(condition: cond, ifBlock: blockStmt)
                }
            }
    }
    
    
    internal func statement() -> GenericParser<String, (), Statement> {
        return ifStatement() <|> question()
    }
    
    
    internal func block() -> GenericParser<String, (), Block> {
        return lexer.braces(
                statement().manyAccumulator { acc, stmts in
                    var tmp = stmts
                    tmp.append(acc)
                    return tmp
                }
            ).map { stmts in Block(block: stmts) }
    }
    
    
    internal func form() -> GenericParser<String, (), Form> {
        return lexer.symbol("form") *> identifier().flatMap { [unowned self] fId in
            return self.block().map { fBlock in Form(identifier: fId, block: fBlock) }
        }
    }
    
    
    
    internal func identifier() -> GenericParser<String, (), Identifier> {
        return lexer.identifier.map { id in Identifier(id: id) }
    }
    
    internal func identifierExpr() -> GenericParser<String, (), Expression> {
        return lexer.identifier.map { id in Identifier(id: id) }
    }
    
    
    private func binary(name: String, function: (Expression, Expression) -> Expression, assoc: Associativity) -> Operator<String, (), Expression> {
        let opParser = lexer.symbol(name) *> GenericParser(result: function)
        return .Infix(opParser, assoc)
    }
    
    private func prefix(name: String, function: Expression -> Expression) -> Operator<String, (), Expression> {
        let opParser = lexer.symbol(name) *> GenericParser(result: function)
        return .Prefix(opParser)
    }
    
    private func opTable0() -> OperatorTable<String, (), Expression> {
        return
            [
                [
                    binary("<=", function: le, assoc: .Left),
                    binary(">=", function: ge, assoc: .Left),
                ],
        ]
    }
    
    private func opTable() -> OperatorTable<String, (), Expression> {
        return
            [
                [
                    prefix("-", function: neg),
                    prefix("!", function: not),
                ],
                [
                    binary("^", function: pow, assoc: .Right),
                ],
                [
                    binary("*", function: mul, assoc: .Left),
                    binary("/", function: div, assoc: .Left),
                ],
                [
                    binary("+", function: add, assoc: .Left),
                    binary("-", function: sub, assoc: .Left),
                ],
                [
                    binary("<", function: lt, assoc: .Left),
                    binary(">", function: gt, assoc: .Left),
                    binary("==", function: eq, assoc: .Left),
                    binary("!=", function: ne, assoc: .Left),
                ],
//                [
//                    binary("<=", function: le, assoc: .Left),
//                    binary(">=", function: ge, assoc: .Left),
//                ],
                [
                    binary("&&", function: and, assoc: .Left),
                    binary("||", function: or, assoc: .Left),
                ]
            ]
    }
    
    private func neg(expression: Expression) -> Expression {
        return Neg(rhs: expression)
    }
    
    private func not(expression: Expression) -> Expression {
        return Not(rhs: expression)
    }
    
    private func add(expressions: (Expression, Expression)) -> Expression {
        return Add(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func sub(expressions: (Expression, Expression)) -> Expression {
        return Sub(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func mul(expressions: (Expression, Expression)) -> Expression {
        return Mul(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func div(expressions: (Expression, Expression)) -> Expression {
        return Div(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func pow(expressions: (Expression, Expression)) -> Expression {
        return Pow(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func gt(expressions: (Expression, Expression)) -> Expression {
        return Gt(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func lt(expressions: (Expression, Expression)) -> Expression {
        return Lt(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func ge(expressions: (Expression, Expression)) -> Expression {
        return Ge(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func le(expressions: (Expression, Expression)) -> Expression {
        return Le(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func eq(expressions: (Expression, Expression)) -> Expression {
        return Eq(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func ne(expressions: (Expression, Expression)) -> Expression {
        return Ne(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func and(expressions: (Expression, Expression)) -> Expression {
        return And(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func or(expressions: (Expression, Expression)) -> Expression {
        return Or(lhs: expressions.0, rhs: expressions.1)
    }
}