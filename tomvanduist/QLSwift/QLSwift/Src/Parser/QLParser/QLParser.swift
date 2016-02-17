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
    
    func parse(ql: QL) throws -> QLForm {
        return try qlParser().run(sourceName: "QL", input: ql)
    }
    
    private func qlParser() -> GenericParser<String, (), QLForm> {
        let lexer   = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
        
        
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
            let stringExpr: GenericParser<String, (), QLExpression> =
                lexer.symbol("string").map { _ in QLString() }
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
//            let infix: GenericParser<String, (), QLInfix.Type> =
//                lexer.symbol("+").map { _ in QLAdd.self } <|>
//                lexer.symbol("-").map { _ in QLSub.self } <|>
//                lexer.symbol("*").map { _ in QLMul.self } <|>
//                lexer.symbol("/").map { _ in QLDiv.self } <|>
//                lexer.symbol("^").map { _ in QLPow.self } <|>
//                lexer.symbol("&&").map { _ in QLAnd.self } <|>
//                lexer.symbol("||").map { _ in QLOr.self } <|>
//                lexer.symbol("==").map { _ in QLEq.self } <|>
//                lexer.symbol("!=").map { _ in QLNe.self } <|>
//                lexer.symbol("<=").map { _ in QLLe.self }.attempt <|>
//                lexer.symbol(">=").map { _ in QLGe.self }.attempt <|>
//                lexer.symbol("<").map { _ in QLLt.self } <|>
//                lexer.symbol(">").map { _ in QLGt.self }

//            var infixExpr: GenericParser<String, (), QLExpression>!
            
            // Left associative infix, TODO: properly define lhs
//            func opParser(lhs: QLExpression) -> GenericParser<String, (), QLExpression> {
//                return infix.flatMap { eInfix in
//                    expr.flatMap { rhs in
//                        opParser1(eInfix.init(lhs: lhs, rhs: rhs))
//                    }
//                }
//            }
//            func opParser1(rhs: QLExpression) -> GenericParser<String, (), QLExpression> {
//                return opParser(rhs) <|> GenericParser(result: rhs)
//            }
//            infixExpr =
//                (moneyExpr <|> prefixExpr <|> precExpr <|> boolExpr <|> stringExpr <|> litExpr <|> varExpr).flatMap { lhs in
//                    opParser(lhs) <|> GenericParser(result: lhs)
//                }
            
           
//            let addOp: GenericParser<String, (), (Int, Int) -> Int> =
//                StringParser.character("+") *> GenericParser(result: +) <|>
//                StringParser.character("-") *> GenericParser(result: -)
//            
//            let integer = StringParser.digit.many1.stringValue.map { Int($0)! }
//            
//            let add = integer.chainLeft(addOp, otherwise: 0)

            
            
//            let infixOp: GenericParser<String, (), (QLExpression, QLExpression) -> QLExpression> = { (e: GenericParser<String, (), (QLExpression, QLExpression)>) in
//            let infixOp: GenericParser<String, (), (QLExpression, QLExpression) -> QLExpression> = { (e: GenericParser<String, (), (QLExpression, QLExpression)>) in
//                infix.flatMap { (eInfix: QLInfix.Type) in
////                    e.map { (lhs: QLExpression, rhs: QLExpression) in GenericParser(result: (eInfix as! QLInfix).dynamicType.init(lhs: lhs, rhs: rhs)) }
//                        e.map { (lhs: QLExpression, rhs: QLExpression) in
////                            return (eInfix.self.init(lhs: lhs, rhs: rhs))
//                            QLAdd(lhs: lhs, rhs: rhs) as QLExpression
//                        }
//                }
//            }
            
//            func reduce(o: (QLExpression, QLExpression)) -> QLExpression {
//                return QLAdd(o: o)
//            }
            
            
//            let infixOp = // : GenericParser<String, (), (QLExpression, QLExpression) -> QLExpression> =
//            lexer.symbol("+") *> GenericParser(result: reduce as (QLExpression, QLExpression) -> QLExpression)

            let infixOp1 = // : GenericParser<String, (), (QLExpression, QLExpression) -> QLExpression> =
                lexer.symbol("+") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLAdd(o: o) }) <|>
                lexer.symbol("-") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLSub(o: o) }) <|>
                lexer.symbol("*") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLMul(o: o) }) <|>
                lexer.symbol("/") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLDiv(o: o) }) <|>
                lexer.symbol("^") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLPow(o: o) }) <|>
                lexer.symbol("&&") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLAnd(o: o) }) <|>
                lexer.symbol("||") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLOr(o: o) })
            let infixOp2 =
                lexer.symbol("==") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLEq(o: o) }) <|>
                lexer.symbol("!=") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLNe(o: o) }) <|>
                lexer.symbol("<=") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLLe(o: o) }).attempt <|>
                lexer.symbol(">=") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLGe(o: o) }).attempt <|>
                lexer.symbol("<") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLLt(o: o) }) <|>
                lexer.symbol(">") *> GenericParser(result: { (o: (QLExpression, QLExpression)) -> QLExpression in QLGt(o: o) })
            let infix = infixOp1 <|> infixOp2
//            lexer.symbol("+").map { _ in QLAdd.self } <|>
//                lexer.symbol("-").map { _ in QLSub.self } <|>
//                lexer.symbol("*").map { _ in QLMul.self } <|>
//                lexer.symbol("/").map { _ in QLDiv.self } <|>
//                lexer.symbol("^").map { _ in QLPow.self } <|>
//                lexer.symbol("&&").map { _ in QLAnd.self } <|>
//                lexer.symbol("||").map { _ in QLOr.self } <|>
//                lexer.symbol("==").map { _ in QLEq.self } <|>
//                lexer.symbol("!=").map { _ in QLNe.self } <|>
//                lexer.symbol("<=").map { _ in QLLe.self }.attempt <|>
//                lexer.symbol(">=").map { _ in QLGe.self }.attempt <|>
//                lexer.symbol("<").map { _ in QLLt.self } <|>
//                lexer.symbol(">").map { _ in QLGt.self }
            
            let infixExpr = expr.chainLeft1(infix)
            
            
            
//            let add = expr.chainLeft(infix, otherwise: 0)
        
            
//            return moneyExpr <|> prefixExpr <|> infixExpr.attempt <|> precExpr <|> boolExpr <|> stringExpr <|> litExpr <|> varExpr

            return moneyExpr <|> precExpr <|> boolExpr <|> stringExpr <|> litExpr <|> varExpr// <|> prefixExpr <|> infixExpr.attempt// <|> precExpr
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
            lexer.symbol("form") *> variable.flatMap { fVar in
                return block.map { fBlock in QLForm(variable: fVar, block: fBlock) }
            }
        
        
        return lexer.whiteSpace *> form <* StringParser.eof
    }
}