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
//  question    -> stringLiteral variable: identifier
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
    
    // MARK: Properties.
    private let lexer = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
    private let symbol = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).symbol
    private let stringLiteral = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).stringLiteral // Includes the quotes.
    private let identifier = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).identifier
    private let colon = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).colon
    private let whiteSpace = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).whiteSpace

    // MARK: Methods.
    
    private func endOfLineParser() -> GenericParser<String, (), Character> {
        let character = StringParser.character
        
        let endOfLine = StringParser.crlf.attempt <|>
            (character("\n") *> character("\r")).attempt <|>
            character("\n") <|>
            character("\r") <?> "end of line"
        
        return endOfLine
    }

    // MARK: Literals.
    private func booleanParser() -> GenericParser<String, (), QLExpression> {
        let qlbooleanTrue: GenericParser<String, (), QLBool> = symbol("true") *> GenericParser(result: QLBool(boolean: true))
        let qlbooleanFalse: GenericParser<String, (), QLBool> = symbol("false") *> GenericParser(result: QLBool(boolean: false))
        return (qlbooleanTrue <|> qlbooleanFalse).map{ QLUnaryExpression(expression: $0) }
    }
    
    private func literalParser() -> GenericParser<String, (), QLExpression> {
        return booleanParser()
    }
    
    // Variable.
    private func variableParser() -> GenericParser<String, (), QLExpression> { return identifier.map{ QLVariable(identifier: $0) } }
    
    
    // MARK: Expression.
    
    private func expressionParser() -> GenericParser<String, (), QLExpression> {
        
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
    
            expression.between(openingParen, closingParen) <|> literalParser() <|> variableParser() <?> "expression"
    
        } <?> "opTable expression"
    
        return qlexpression
    }
    
    func parseStream(data: String) throws -> QLForm {
        return try parser().run(sourceName: "", input: data)
    }
    
    private func parser() -> GenericParser<String, (), QLForm> { return whiteSpace *> formParser() }

    // "name" variable: type
    private func questionParser() -> GenericParser<String, (), QLStatement> {
        return (stringLiteral <?> "question name").flatMap{ name in
            (self.variableParser() <* self.colon <?> "question variable").flatMap{ variable -> GenericParser<String, (), QLStatement> in
                (StringParser.noneOf("\r\n,\n\r").many.stringValue <* self.endOfLineParser() <* self.whiteSpace <?> "type identifier").map{ type in
                    return QLQuestion(name: name, variable: variable, type: type)
                }
            }
        }
    }
    
    private func ifParser() -> GenericParser<String, (), QLStatement> {
        return (symbol("if") *> expressionParser()).flatMap{ condition in
            (self.whiteSpace *> self.codeBlockParser()).map { codeBlock in
                QLIfStatement(condition: condition, codeBlock: codeBlock)
            }
        }
    }
    
    private func codeBlockParser() -> GenericParser<String, (), [QLStatement]> {
        let qlstatement = questionParser() <|> ifParser()

        let qlstatements: GenericParser<String, (), [QLStatement]> = qlstatement.manyAccumulator { (let statement, var accumulated) in
            print("Statement: \(statement)")
            accumulated.append(statement)
            return accumulated
        }

        return lexer.braces(qlstatements)
    }
    
    private func formParser() -> GenericParser<String, (), QLForm> {
        let form = symbol("form") *> identifier.flatMap{ (formName) -> GenericParser<String, (), QLForm> in

            print("Form name: \(formName)")

            let temp = self.codeBlockParser().map{ (let block: [QLStatement]) -> QLForm in
                print("Block: \(block)")
                return QLForm(formName: formName, codeBlock: block)
            }

            print("Temp: \(temp)")

            return temp
        }  <?> "Error at the end of the form."
        
        return form
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