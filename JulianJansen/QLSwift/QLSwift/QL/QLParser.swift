//
//  QLParser.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
// 
//  Grammar:
//  
//  ->      "can consist of"
//  |       Alternative grammar productions
//  *       Kleene star (zero or more)
//
//  form        -> form codeBlock
//  codeBlock   -> { statement* }
//  statement   -> question | if ( expression ) codeBlock
//  question    -> stringLiteral identifier: literal
//  expression  ->
//  literal     -> integerLiteral | floatLiteral | stringLiteral | boolLiteral

// &&, ||, !, <, >, >=, <=, !=, ==, +, -, *, /.


//
//  Swift Reference: https://developer.apple.com/library/ios/documentation/Swift/Conceptual/
//  Swift_Programming_Language/AboutTheLanguageReference.html
//
//  Parsec to Swift
//  <^>  ->  .map
//  >>-  ->  .flatMap

import Foundation
import SwiftParsec

class QLParser {
        
    func parseStream(data: String) throws -> QLForm {
        return try parser().run(sourceName: "", input: data)
    }
    
    private func parser() -> GenericParser<String, (), QLForm> {
        
        let ql = LanguageDefinition<()>.ql
        let lexer = GenericTokenParser(languageDefinition: ql)
        
        let noneOf = StringParser.noneOf
        let character = StringParser.character
        let symbol = lexer.symbol
        let stringLiteral = lexer.stringLiteral // Includes the quotes.
        let identifier = lexer.identifier
        let colon = lexer.colon
        let whiteSpace = lexer.whiteSpace
        let endOfLine = StringParser.crlf.attempt <|>
            (character("\n") *> character("\r")).attempt <|>
            character("\n") <|>
            character("\r") <?> "end of line"
        
        
        // Literal.
        let qlbooleanTrue: GenericParser<String, (), QLBool> = symbol("true") *> GenericParser(result: QLBool(boolean: true))
        let qlbooleanFalse: GenericParser<String, (), QLBool> = symbol("false") *> GenericParser(result: QLBool(boolean: false))
        let qlboolean: GenericParser<String, (), QLExpression> = (qlbooleanTrue <|> qlbooleanFalse).map{ QLUnaryExpression(expression: $0) }
        
        
        
        let qlliteral: GenericParser<String, (), QLExpression> = qlboolean
        
        // Variable.
        let qlvariable: GenericParser<String, (), QLExpression> = identifier.map{ QLVariable(identifier: $0) }

        
        // MARK: Question.
        
        // "name" variable: type
        let qlquestion: GenericParser<String, (), QLStatement> = (stringLiteral <?> "question name").flatMap{ name in
            (qlvariable <* colon <?> "question variable").flatMap{ variable -> GenericParser<String, (), QLStatement> in
                (noneOf("\r\n,\n\r").many.stringValue <* endOfLine <* whiteSpace <?> "type identifier").map{ type in
                    QLQuestion(name: name, variable: variable, type: type)
                }
            }
        }
        
        
        // MARK: Expression.
        
        // Based on opTable from ExpressionTests.swift of SwiftParsec.
        // &&, ||, !, <, >, >=, <=, !=, ==, +, -, *, /.
        
        let opTable: OperatorTable<String, (), QLExpression> = [
            
            [
                binary("&&", function: andExpression, assoc: .Left)
            ]
            
        ]
        
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        
        let qlexpression: GenericParser<String, (), QLExpression> = opTable.expressionParser { expression in
            
            expression.between(openingParen, closingParen) <|> qlliteral <|> qlvariable <?> "expression"
            
            } <?> "opTable expression"
        
        let codeBlock: GenericParser<String, (), [QLStatement]>!
        
        let qlif: GenericParser<String, (), QLStatement> = (symbol("if") *> qlexpression).flatMap{ condition in
            codeBlock.map { codeBlock in
                QLIfStatement(condition: condition, codeBlock: codeBlock)
            }
        }
    
        
        // MARK: Statement.
        
        let qlstatement = qlquestion <|> qlif
        
        let qlstatements: GenericParser<String, (), [QLStatement]> = qlstatement.manyAccumulator { (let statement, var accumulated) in
            print("Statement: \(statement)")
            accumulated.append(statement)
            return accumulated
        }
        
        codeBlock = lexer.braces(qlstatements)
        
        let form = symbol("form") *> identifier.flatMap{ (formName) -> GenericParser<String, (), QLForm> in
            
            print("Form name: \(formName)")
            
            let temp = codeBlock.map{ (let block: [QLStatement]) -> QLForm in
                print("Block: \(block)")
                return QLForm(formName: formName, codeBlock: block)
            }
            
            print("Temp: \(temp)")
            
            return temp
        }  <?> "Error at the end of the form."
        
        return whiteSpace *> form
    }
    
    // MARK: Expression operators.
    // Partly based on functions from ExpressionTests.swift of SwiftParsec.
    private func andExpression(lhs: QLExpression, rhs: QLExpression) -> QLExpression {
        print("In andOperator method")
        return QLAndExpression(lhs: lhs, rhs: rhs)
    }
    
    private func binary(name: String, function: (QLExpression, QLExpression) -> QLExpression, assoc: Associativity) -> Operator<String, (), QLExpression> {
        
        let opParser = StringParser.string(name) *> GenericParser(result: function)
        return .Infix(opParser, assoc)
        
    }
    
    private func prefix(name: String, function: QLExpression -> QLExpression) -> Operator<String, (), QLExpression> {
        
        let opParser = StringParser.string(name) *> GenericParser(result: function)
        return .Prefix(opParser)
        
    }
    
    private func postfix(name: String, function: QLExpression -> QLExpression) -> Operator<String, (), QLExpression> {
        
        let opParser = StringParser.string(name) *> GenericParser(result: function)
        return .Postfix(opParser.attempt)
        
    }
}