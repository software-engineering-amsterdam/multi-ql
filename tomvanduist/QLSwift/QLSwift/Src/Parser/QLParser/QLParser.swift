//
//  QLParser.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation
import SwiftParsec


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
            let varExpr: GenericParser<String, (), QLExpression> =
                variable.map { eVar in QLExpressionVariable(variable: eVar) }
            let prefixExpr: GenericParser<String, (), QLExpression> =
                symbol("-") *> _expr.map { QLNeg(rhs: $0) } <|>
                symbol("!") *> _expr.map { QLNot(rhs: $0) }
            let precExpr: GenericParser<String, (), QLExpression> =
                lexer.parentheses(_expr)
            
            expr = precExpr <|> varExpr <|> prefixExpr
            
            return expr
        }
        
        let question: GenericParser<String, (), QLQuestion> =
            variable.flatMap { qVar in
                return lexer.colon *> stringLit.flatMap { qLit in
                    return expr.flatMap { qExpr in
                        return GenericParser(result: QLQuestion(variable: qVar, string: qLit as! QLStringLiteral, expression: qExpr))
                    }
                }
            }
        
        
        let qStmt: GenericParser<String, (), QLStatement> =
            question.map { sQuestion in QLQuestionStatement(question: sQuestion) }
        let stmtList: GenericParser<String, (), QLStatement> =
            lexer.braces(
                qStmt.manyAccumulator { acc, stmts in
                    var tmp = stmts
                    tmp.append(acc)
                    return tmp
                }
            ).map { stmts in QLStatementList(statements: stmts) }
        let stmt: GenericParser<String, (), QLStatement> =
            stmtList <|> qStmt
        
        
        let form: GenericParser<String, (), QLForm> =
            symbol("form") *> variable.flatMap { fVar in
                return stmt.map { fStmt in QLForm(variable: fVar, statement: fStmt) }
            }
        
        
        return lexer.whiteSpace *> form
    }
}