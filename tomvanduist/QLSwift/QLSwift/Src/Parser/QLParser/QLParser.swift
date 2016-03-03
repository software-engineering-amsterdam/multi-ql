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
 * form             ::= 'form' block
 * block            ::= { stmt* }
 * stmt             ::= 'if' ( expr) { block } | question
 * question         ::= computedQuestion | variableQuestion
 * computedQuestion ::= var ':' stringLit expr
 * variableQuestion ::= var ':' stringLit type
 * type             ::= integer | boolean | string
 * expr             ::= unaryOp expr | expr binary expr | ( expr ) | literal | var
 * literal          ::= true | false | stringLit | integerLit
 * unaryOp          ::= - | !
 * binary           ::= + | - | * | / | ^ | || | && | < | <= | == | >= | >
 * var              ::= identifier
 */
class QLParser: NSObject {
    
    let lexer   = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
    
    
    func parse(ql: QL) throws -> QLForm {
        return try (lexer.whiteSpace *> form() <* StringParser.eof).run(sourceName: "QL", input: ql)
    }
}


extension QLParser {
    
    private func form() -> GenericParser<String, (), QLForm> {
        return lexer.symbol("form") *> identifier().flatMap { [unowned self] fId in
            self.block().map { fBlock in
                QLForm(identifier: fId, block: fBlock)
            }
        }
    }
    
    private func block() -> GenericParser<String, (), QLBlock> {
        return lexer.braces(
            statement().manyAccumulator { (acc, var stmts) in
                stmts.append(acc)
                return stmts
            }
            ).map { stmts in QLBlock(block: stmts) }
    }
    
    private func statement() -> GenericParser<String, (), QLStatement> {
        return ifStatement() <|> question()
    }
    
    private func question() -> GenericParser<String, (), QLStatement> {
        return computedQuestion().attempt <|> variableQuestion()
    }
    
    private func computedQuestion() -> GenericParser<String, (), QLStatement> {
        return identifier().flatMap { [unowned self] qId in
            self.lexer.colon *> self.lexer.stringLiteral.flatMap { [unowned self] qLit in
                self.expr().map { qExpr in
                    QLComputedQuestion(identifier: qId, label: qLit, expression: qExpr)
                }
            }
        }
    }
    
    private func variableQuestion() -> GenericParser<String, (), QLStatement> {
        return identifier().flatMap { [unowned self] qId in
            self.lexer.colon *> self.lexer.stringLiteral.flatMap { [unowned self] qLit in
                self.type().map { qType in
                    QLVariableQuestion(identifier: qId, label: qLit, type: qType)
                }
            }
        }
    }
    
    private func ifStatement() -> GenericParser<String, (), QLStatement> {
        return lexer.symbol("if") *> lexer.parentheses(expr()).flatMap { [unowned self] cond in
            self.block().map { blockStmt in
                QLConditional(condition: cond, ifBlock: blockStmt)
            }
        }
    }
    
    private func expr() -> GenericParser<String, (), QLExpression> {
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        
        
        // Recursive definition of simple expression
        let expr: GenericParser<String, (), QLExpression> =
            variable() <|> literalExpr()
        
        // Expression between ( )
        let precExpr: GenericParser<String, (), QLExpression> =
            lexer.parentheses(expr)
        
        
        // Need to use 2 operator tables to properly parse '<=' and '<' etc
        let _expr0: GenericParser<String, (), QLExpression> =
            opTable0().expressionParser { e in
                (e.between(openingParen, closingParen) <* lexer.whiteSpace) <|> precExpr <|> expr
            }
        let _expr: GenericParser<String, (), QLExpression> =
            opTable().expressionParser { e in
                (e.between(openingParen, closingParen) <* lexer.whiteSpace) <|> precExpr <|> expr
            }
        
        
        // Attempt to find normal' operator table first, '<=' operator table last
        // Using .attempt will ignore errors when '<' is exptected but '<=' is found, last expr will pick that up
        return lexer.whiteSpace *> (_expr.attempt <|> _expr0)
    }
    
    private func literalExpr() -> GenericParser<String, (), QLExpression> {
        return literal().map { qLiteral in
            QLLiteralExpression(literal: qLiteral)
        }
    }
    
    private func literal() -> GenericParser<String, (), QLLiteral> {
        let boolLit: GenericParser<String, (), QLLiteral> =
            lexer.symbol("true") *> GenericParser(result: QLBooleanLiteral(bool: true)) <|>
            lexer.symbol("false") *> GenericParser(result: QLBooleanLiteral(bool: false))
        let stringLit: GenericParser<String, (), QLLiteral> =
            lexer.stringLiteral.map{ s in QLStringLiteral(string: s) }
        let intLit: GenericParser<String, (), QLLiteral> =
            lexer.integer.map { i in QLIntegerLiteral(integer: i) }
        
        return boolLit <|> stringLit <|> intLit
    }
    
    private func type() -> GenericParser<String, (), QLType> {
        let boolType: GenericParser<String, (), QLType> =
            lexer.symbol("boolean").map { _ in QLBooleanType() }
        let stringType: GenericParser<String, (), QLType> =
            lexer.symbol("string").map { _ in QLStringType() }
        let integerType: GenericParser<String, (), QLType> =
            lexer.symbol("integer").map { _ in QLIntegerType() }
        
        return boolType <|> stringType <|> integerType
    }
    
    private func identifier() -> GenericParser<String, (), QLIdentifier> {
        return lexer.identifier.map { id in QLIdentifier(id: id) }
    }
    
    private func variable() -> GenericParser<String, (), QLExpression> {
        return lexer.identifier.map { id in QLVariable(id: id) }
    }
    
    private func binary(name: String, function: (QLExpression, QLExpression) -> QLExpression, assoc: Associativity) -> Operator<String, (), QLExpression> {
        let opParser = lexer.symbol(name) *> GenericParser(result: function)
        return .Infix(opParser, assoc)
    }
    
    private func prefix(name: String, function: QLExpression -> QLExpression) -> Operator<String, (), QLExpression> {
        let opParser = lexer.symbol(name) *> GenericParser(result: function)
        return .Prefix(opParser)
    }
    
    private func opTable0() -> OperatorTable<String, (), QLExpression> {
        return
            [
                [
                    binary("<=", function: le, assoc: .Left),
                    binary(">=", function: ge, assoc: .Left),
                ],
        ]
    }
    
    private func opTable() -> OperatorTable<String, (), QLExpression> {
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
    
    private func neg(expression: QLExpression) -> QLExpression {
        return QLNeg(rhs: expression)
    }
    
    private func not(expression: QLExpression) -> QLExpression {
        return QLNot(rhs: expression)
    }
    
    private func add(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLAdd(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func sub(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLSub(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func mul(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLMul(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func div(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLDiv(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func pow(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLPow(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func gt(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLGt(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func lt(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLLt(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func ge(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLGe(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func le(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLLe(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func eq(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLEq(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func ne(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLNe(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func and(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLAnd(lhs: expressions.0, rhs: expressions.1)
    }
    
    private func or(expressions: (QLExpression, QLExpression)) -> QLExpression {
        return QLOr(lhs: expressions.0, rhs: expressions.1)
    }
}