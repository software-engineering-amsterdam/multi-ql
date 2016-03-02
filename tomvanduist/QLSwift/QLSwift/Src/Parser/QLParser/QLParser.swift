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
    
    
    func parse(ql: QL) throws -> Form {
        return try (lexer.whiteSpace *> form() <* StringParser.eof).run(sourceName: "QL", input: ql)
    }
}


extension QLParser {
    
    private func form() -> GenericParser<String, (), Form> {
        return lexer.symbol("form") *> identifier().flatMap { [unowned self] fId in
            self.block().map { fBlock in
                Form(identifier: fId, block: fBlock)
            }
        }
    }
    
    private func block() -> GenericParser<String, (), Block> {
        return lexer.braces(
            statement().manyAccumulator { (acc, var stmts) in
                stmts.append(acc)
                return stmts
            }
            ).map { stmts in Block(block: stmts) }
    }
    
    private func statement() -> GenericParser<String, (), Statement> {
        return ifStatement() <|> question()
    }
    
    private func question() -> GenericParser<String, (), Statement> {
        return identifier().flatMap { [unowned self] qId in
            self.lexer.colon *> self.lexer.stringLiteral.flatMap { [unowned self] qLit in
                self.expr().map { qExpr in
                    Question(identifier: qId, label: qLit, expression: qExpr)
                }
            }
        }
    }
    
    private func ifStatement() -> GenericParser<String, (), Statement> {
        return lexer.symbol("if") *> lexer.parentheses(expr()).flatMap { [unowned self] cond in
            self.block().map { blockStmt in
                Conditional(condition: cond, ifBlock: blockStmt)
            }
        }
    }
    
    private func expr() -> GenericParser<String, (), Expression> {
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        
        // Recursive definition of simple expression
        let expr: GenericParser<String, (), Expression> = GenericParser.recursive {
            (expr: GenericParser<String, (), Expression>) in
            
            let boolLit: GenericParser<String, (), Expression> =
                lexer.symbol("true") *> GenericParser(result: BooleanLiteral(bool: true)) <|>
                lexer.symbol("false") *> GenericParser(result: BooleanLiteral(bool: false))
            let stringLit: GenericParser<String, (), Expression> =
                lexer.stringLiteral.map{ s in StringLiteral(string: s) }
            let intLit: GenericParser<String, (), Expression> =
                lexer.integer.map { i in IntegerLiteral(integer: i) }
            let litExpr: GenericParser<String, (), Expression> =
                boolLit <|> stringLit <|> intLit
            let boolExpr: GenericParser<String, (), Expression> =
                lexer.symbol("boolean").map { _ in BooleanField() }
            let stringExpr: GenericParser<String, (), Expression> =
                lexer.symbol("string").map { _ in StringField() }
            let moneyExpr: GenericParser<String, (), Expression> =
                lexer.symbol("money").map { _ in MoneyField() }
            
            return moneyExpr <|> boolExpr <|> stringExpr <|> litExpr <|> identifierExpr()
        }
        
        // Expression between ( )
        let precExpr: GenericParser<String, (), Expression> =
            lexer.parentheses(expr)
        
        
        // Need to use 2 operator tables to properly parse '<=' and '<' etc
        let _expr0: GenericParser<String, (), Expression> =
            opTable0().expressionParser { e in
                (e.between(openingParen, closingParen) <* lexer.whiteSpace) <|> precExpr <|> expr
            }
        let _expr: GenericParser<String, (), Expression> =
            opTable().expressionParser { e in
                (e.between(openingParen, closingParen) <* lexer.whiteSpace) <|> precExpr <|> expr
            }
        
        
        // Calculated money expression
        let calcMoneyExpr: GenericParser<String, (), Expression> =
            lexer.symbol("money") *> lexer.parentheses(_expr).map { e in MoneyField(expression: e) }
        
        
        // Attempt to find calculated money and 'normal' operator table first, '<=' operator table last
        // Using .attempt will ignore errors when '<' is exptected but '<=' is found, last expr will pick that up
        return lexer.whiteSpace *> (calcMoneyExpr.attempt <|> _expr.attempt <|> _expr0)
    }
    
    private func identifier() -> GenericParser<String, (), Identifier> {
        return lexer.identifier.map { id in Identifier(id: id) }
    }
    
    private func identifierExpr() -> GenericParser<String, (), Expression> {
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