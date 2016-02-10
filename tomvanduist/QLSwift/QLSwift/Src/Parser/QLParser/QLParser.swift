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
 * stmt         ::= question
 * question     ::= var ':' stringLit expr
 * expr         ::= ( expr ) | prefix expr | expr infix expr | var | boolean | money | const
 * const        ::= true | false | stringLit | numberLit
 * prefix       ::= - | !
 * infix        ::= + | - | * | / | ^ | || | &&
 * var          ::= identifier
 * money        ::= 'money' || 'money' ( expr )
 */
class QLParser: NSObject {
    
    func parse(ql: String) throws -> QLForm {
        do {
            return try qlParser().run(sourceName: "QL", input: ql)
        } catch let error {
            print(error)
            throw error
        }
    }
    
    private func qlParser() -> GenericParser<String, (), QLForm> {
        let lexer   = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
        let symbol  = lexer.symbol
        
        
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
            intLit <|> floatLit
        
        
        var expr: GenericParser<String, (), QLExpression>!
        
        GenericParser.recursive { (_expr: GenericParser<String, (), QLExpression>) in
            let precExpr: GenericParser<String, (), QLExpression> =
                lexer.parentheses(_expr)
            let litExpr: GenericParser<String, (), QLExpression> =
                (boolLit <|> stringLit <|> number).map { literal in
                    QLExpressionLiteral(literal: literal)
                }
            let varExpr: GenericParser<String, (), QLExpression> =
                variable.map { eVar in QLExpressionVariable(variable: eVar) }
            let boolExpr: GenericParser<String, (), QLExpression> =
                lexer.symbol("boolean").map { _ in QLBoolean() }
            let prefix: GenericParser<String, (), QLPrefix.Type> =
                StringParser.character("-").map { _ in QLNeg.self } <|>
                StringParser.character("!").map { _ in QLNot.self }
            let prefixExpr: GenericParser<String, (), QLExpression> =
                prefix.flatMap { ePrefix in
                    _expr.map { rhs in ePrefix.init(rhs: rhs) }
                }
            let infix: GenericParser<String, (), QLInfix.Type> =
                lexer.symbol("+").map { _ in QLAdd.self } <|>
                StringParser.character("-").map { _ in QLSub.self } <|>
                StringParser.character("*").map { _ in QLMul.self } <|>
                StringParser.character("/").map { _ in QLDiv.self } <|>
                StringParser.character("^").map { _ in QLPow.self }
            let infixExpr: GenericParser<String, (), QLExpression> =
                varExpr.flatMap { lhs in
                    infix.flatMap { eInfix in
                        varExpr.map { rhs in
                            eInfix.init(lhs: lhs, rhs: rhs)
                        }
                    }
                }
            
            
            expr = precExpr <|> prefixExpr <|> infixExpr.attempt <|> varExpr <|> boolExpr <|> litExpr
            
            return expr
        }
        
        let question: GenericParser<String, (), QLQuestion> =
            variable.flatMap { qVar in
                lexer.colon *> stringLit.flatMap { qLit in
                    expr.map { qExpr in
                        QLQuestion(variable: qVar, string: qLit as! QLStringLiteral, expression: qExpr)
                    }
                }
            }
        
        
        var stmt: GenericParser<String, (), QLStatement>!
        var block: GenericParser<String, (), QLBlockStatement>!
        
        GenericParser.recursive { (_stmt: GenericParser<String, (), QLStatement>) in
            let qStmt: GenericParser<String, (), QLStatement> =
                question.map { sQuestion in QLQuestionStatement(question: sQuestion) }
            let ifStmt: GenericParser<String, (), QLStatement> =
                lexer.symbol("if") *> lexer.parentheses(expr).flatMap { cond in
                    block.map { blockStmt in
                        QLIf(conditional: cond, block: blockStmt)
                    }
                }

            
            block =
                lexer.braces(
                    _stmt.manyAccumulator { acc, stmts in
                        var tmp = stmts
                        tmp.append(acc)
                        return tmp
                    }
                ).map { stmts in QLBlockStatement(block: stmts) }
            
            
            stmt = ifStmt <|> qStmt
            
            return stmt
        }
        
        
        let form: GenericParser<String, (), QLForm> =
            symbol("form") *> variable.flatMap { fVar in
                return block.map { fStmt in QLForm(variable: fVar, statement: fStmt) }
            }
        
        
        return lexer.whiteSpace *> form
    }
}