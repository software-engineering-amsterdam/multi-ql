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
 * type             ::= integer | float | boolean | string
 * expr             ::= unaryOp expr | expr binary expr | ( expr ) | literal | var
 * literal          ::= true | false | stringLit | integerLit
 * unaryOp          ::= - | !
 * binary           ::= + | - | * | / | ^ | || | && | < | <= | == | >= | >
 * var              ::= identifier
 *
 */
class QLParser: NSObject {
    let lexer   = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
    
    
    func parse(ql: QL) throws -> QLForm {
        return try (lexer.whiteSpace *> form() <* StringParser.eof).run(sourceName: "QL", input: ql)
    }
    
    private func form() -> GenericParser<String, (), QLForm> {
        return lexer.symbol("form") *> identifier().flatMap { [unowned self] fId in
            self.block().map { fBlock in
                QLForm(identifier: fId, block: fBlock)
            }
        }
    }
    
    private func block() -> GenericParser<String, (), QLBlock> {
        return lexer.braces(
            statement().manyAccumulator { (acc, stmts) in
                var newStmts = stmts
                
                newStmts.append(acc)
                return newStmts
            }
        ).map { stmts in QLBlock(block: stmts) }
    }
    
    private func statement() -> GenericParser<String, (), QLStatement> {
        return ifStatement().attempt <|> question()
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
        
        let expr: GenericParser<String, (), QLExpression> =
            variable() <|> literalExpr()
        
        // Expression between ( )
        let precExpr: GenericParser<String, (), QLExpression> =
            lexer.parentheses(expr)
        
        return opTable().expressionParser { e in
                (e.between(openingParen, closingParen) <* lexer.whiteSpace) <|> precExpr <|> expr
            }
    }
    
    private func literalExpr() -> GenericParser<String, (), QLExpression> {
        return literal().map { qLiteral in
            QLLiteralExpression(literal: qLiteral)
        }
    }
    
    private func literal() -> GenericParser<String, (), QLLiteral> {
        let boolLit: GenericParser<String, (), QLLiteral> =
            lexer.symbol("true") *> GenericParser(result: QLBooleanLiteral(value: true)) <|>
            lexer.symbol("false") *> GenericParser(result: QLBooleanLiteral(value: false))
        let stringLit: GenericParser<String, (), QLLiteral> =
            lexer.stringLiteral.map{ s in QLStringLiteral(value: s) }
        let intLit: GenericParser<String, (), QLLiteral> =
            lexer.integer.map { i in QLIntegerLiteral(value: i) }
        let floatLit: GenericParser<String, (), QLLiteral> =
            lexer.float.map { f in QLFloatLiteral(value: QLFloat(f)) }
        
        return boolLit <|> stringLit <|> floatLit.attempt <|> intLit
    }
    
    private func type() -> GenericParser<String, (), QLType> {
        let boolType: GenericParser<String, (), QLType> =
            lexer.symbol("boolean").map { _ in QLBooleanType() }
        let stringType: GenericParser<String, (), QLType> =
            lexer.symbol("string").map { _ in QLStringType() }
        let integerType: GenericParser<String, (), QLType> =
            lexer.symbol("integer").map { _ in QLIntegerType() }
        let floatType: GenericParser<String, (), QLType> =
            lexer.symbol("float").map { _ in QLFloatType() }
        
        return boolType <|> stringType <|> integerType <|> floatType
    }
    
    private func identifier() -> GenericParser<String, (), QLIdentifier> {
        return lexer.identifier.map { id in QLIdentifier(id: id) }
    }
    
    private func variable() -> GenericParser<String, (), QLExpression> {
        return lexer.identifier.map { id in QLVariable(id: id) }
    }
    
    private func operatorParser<T, U>(op: String, function: U -> T) -> GenericParser<String, (), U -> T> {
        return lexer.symbol(op) *> GenericParser(result: function)
    }
    
    private func binary(name: String, function: (QLExpression, QLExpression) -> QLExpression, assoc: Associativity) -> Operator<String, (), QLExpression> {
        let opParser = operatorParser(name, function: function)
        return .Infix(opParser, assoc)
    }
    
    /**
     * Workaround to be able to parse both >= and > operators
     */
    private func binary(first: (name: String, function: (QLExpression, QLExpression) -> QLExpression),
                  alternative: (name: String, function: (QLExpression, QLExpression) -> QLExpression),
                        assoc: Associativity) -> Operator<String, (), QLExpression> {
                            
        let opParser =  operatorParser(first.name, function: first.function).attempt
                            <|>
                        operatorParser(alternative.name, function: alternative.function)
                            
        return .Infix(opParser, assoc)
    }
    
    private func prefix(name: String, function: QLExpression -> QLExpression) -> Operator<String, (), QLExpression> {
        let opParser = operatorParser(name, function: function)
        return .Prefix(opParser)
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
                    binary(("<=", le), alternative: ("<", lt), assoc: .Left),
                    binary((">=", ge), alternative: (">", gt), assoc: .Left),
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