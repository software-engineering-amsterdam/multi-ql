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
        let qlvariable: GenericParser<String, (), QLVariable> = identifier.map{ QLVariable(identifier: $0) }

        // MARK: Question.
        
        // "Did you sell a house in 2010?"
        //     hasSoldHouse: boolean
        
//        let qlquestionVariable = identifier <* colon <* symbol("boolean") <?> "Quote/endOfLine at end of question variable."
        
        let qlquestion: GenericParser<String, (), QLQuestion> = (stringLiteral <?> "question name").flatMap{ name in

            let temp = (qlvariable <* colon <?> "question variable").flatMap{ variable -> GenericParser<String, (), QLQuestion> in
                
                print(name)
                print(variable.identifier)
                
                let temp2 = (noneOf("\r\n,\n\r").many.stringValue <* endOfLine <* whiteSpace <?> "type identifier").map{ type in
                    
                    QLQuestion(name: name, variable: variable, type: type)
                
                }
                
                return temp2
            }
            
            return temp
        }
        
        // MARK: Expression.
        
        // Based on opTable from ExpressionTests.swift of SwiftParsec.
        
        // &&, ||, !, <, >, >=, <=, !=, ==, +, -, *, /.
        
        let opTable: OperatorTable<String, (), Int> = [
            
            [
                prefix("-", function: -),
                prefix("+", function: { $0 })
            ],
            [
                postfix("++", function: { (var num) in num++ })
            ],
            [
                binary(">>", function: >>, assoc: .None),
                binary("<<", function: <<, assoc: .None)
            ],
            [
                binary("*", function: *, assoc: .Left),
                binary("/", function: /, assoc: .Left)
            ],
            [
                binary("+", function: +, assoc: .Left),
                binary("-", function: -, assoc: .Left)
            ]
            
        ]
        
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        let decimal = GenericTokenParser<()>.decimal
        
        let qlexpression = opTable.expressionParser { expression in
            
            expression.between(openingParen, closingParen) <|>
                decimal <?> "simple expression"
            
            } <?> "expression"
        
        let qlif = symbol("if") *> qlexpression
    
        // MARK: Statement.
        
        let qlstatement = qlquestion
        
        let qlstatements: GenericParser<String, (), [QLStatement]> = qlstatement.manyAccumulator { (let statement, var accumulated) in
            print("Statement: \(statement)")
            accumulated.append(statement)
            return accumulated
        }
        
        let codeBlock: GenericParser<String, (), [QLStatement]> = lexer.braces(qlstatements) <?> "Error parsing in the braces of code block."
        
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
    
    // Based on functions from ExpressionTests.swift of SwiftParsec.
    private func binary(name: String, function: (Int, Int) -> Int, assoc: Associativity) -> Operator<String, (), Int> {
        
        let opParser = StringParser.string(name) *> GenericParser(result: function)
        return .Infix(opParser, assoc)
        
    }
    
    private func prefix(name: String, function: Int -> Int) -> Operator<String, (), Int> {
        
        let opParser = StringParser.string(name) *> GenericParser(result: function)
        return .Prefix(opParser)
        
    }
    
    private func postfix(name: String, function: Int -> Int) -> Operator<String, (), Int> {
        
        let opParser = StringParser.string(name) *> GenericParser(result: function)
        return .Postfix(opParser.attempt)
        
    }
}