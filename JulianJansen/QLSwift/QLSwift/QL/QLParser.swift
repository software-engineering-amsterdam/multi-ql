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
    private let integerLiteral = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).integer
    private let floatLiteral = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).float
    private let stringLiteral = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).stringLiteral // Includes the quotes.
    private let identifier = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).identifier
    private let colon = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).colon
    private let whiteSpace = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).whiteSpace

    // MARK: Methods.
    
    func parseStream(data: String) throws -> QLForm { return try parser().run(sourceName: "", input: data) }
    
    private func parser() -> GenericParser<String, (), QLForm> { return whiteSpace *> formParser() }

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
    
    private func stringParser() -> GenericParser<String, (), QLExpression> {
        return stringLiteral.map{ QLString(string: $0) }.map{ QLUnaryExpression(expression: $0) }
    }
    
    private func integerParser() -> GenericParser<String, (), QLExpression> {
        return integerLiteral.map{ QLInteger(integer: $0) }.map{ QLUnaryExpression(expression: $0) }
    }
    
    /// Parses a positive whole number in the decimal system. Returns the value of the number as a QLUnaryExpression.
    private func decimalParser() -> GenericParser<String, (), QLExpression> {
        return GenericTokenParser<()>.decimal.map{ QLDecimal(decimal: $0) }.map{ QLUnaryExpression(expression: $0) }
    }
    
//    private func dateParser() -> GenericParser<String, (), QLExpression> {
//        // DD-MM-YYYY
//        return integerLiteral.flatMap{ day in
//            (self.symbol("-") *> self.integerLiteral).flatMap{ month in
//                (self.symbol("-") *> self.integerLiteral).map { year in
//                    QLUnaryExpression(expression: QLDate(day: day, month: month, year: year))
//                }
//            }
//        }
//    }
    
    private func literalParser() -> GenericParser<String, (), QLExpression> {
        return booleanParser() <|> stringParser() <|> integerParser() /* <|> decimalParser() */
    }
    
    // Variable.
    private func variableParser() -> GenericParser<String, (), QLExpression> { return identifier.map{ QLVariable(identifier: $0) } }
    
    
    // MARK: Expression.
    private func singleSymbolExpressionParser() -> GenericParser<String, (), QLExpression> {
        
        // Based on opTable from ExpressionTests.swift of SwiftParsec.
        let singleSymbolOperatorTable: OperatorTable<String, (), QLExpression> = [
            [
                prefix("!", function: { QLNotExpression(expression: $0) }),
            ],
            [
                binary("<", function: { QLSmallerThanExpression(lhs: $0, rhs: $1) }, assoc: .None),
                binary(">", function: { QLGreaterThanExpression(lhs: $0, rhs: $1) }, assoc: .None)
            ],
            [
                binary("*", function: { QLMultiplyExpression(lhs: $0, rhs: $1) }, assoc: .Left),
                binary("/", function: { QLDivideExpression(lhs: $0, rhs: $1) }, assoc: .Left)
            ],
            [
                binary("+", function: { QLAddExpression(lhs: $0, rhs: $1) }, assoc: .Left),
                binary("-", function: { QLSubtractExpression(lhs: $0, rhs: $1) }, assoc: .Left)
            ]
        ]
        
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        
        return singleSymbolOperatorTable.expressionParser { (expression: GenericParser<String, (), QLExpression>) in
            
            expression.between(openingParen, closingParen) <|> literalParser() <|> variableParser() <?> "single symbol expression"
            
        }
    }
    
    private func doubleSymbolExpressionParser() -> GenericParser<String, (), QLExpression> {
        let doubleSymbolOperatorTable: OperatorTable<String, (), QLExpression> = [
            [
                binary(">=", function: { QLGreaterOrIsExpression(lhs: $0, rhs: $1) }, assoc: .None),
                binary("<=", function: { QLSmallerOrISExpression(lhs: $0, rhs: $1) }, assoc: .None)
            ],
            [
                binary("!=", function: { QLIsNotExpression(lhs: $0, rhs: $1) }, assoc: .None),
                binary("==", function: { QLIsExpression(lhs: $0, rhs: $1) }, assoc: .None)
            ],
            [
                binary("&&", function: { QLAndExpression(lhs: $0, rhs: $1) }, assoc: .Left),
                binary("||", function: { QLOrExpression(lhs: $0, rhs: $1) }, assoc: .Left)
            ]
        ]
        
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        
        return doubleSymbolOperatorTable.expressionParser { (expression: GenericParser<String, (), QLExpression>) in
            
            expression.between(openingParen, closingParen) <|> literalParser() <|> variableParser() <?> "double symbol expression"
            
        }
    }
    
    private func expressionParser() -> GenericParser<String, (), QLExpression> {
        print(singleSymbolExpressionParser())
        print(doubleSymbolExpressionParser())
        return singleSymbolExpressionParser().attempt <|> doubleSymbolExpressionParser()
    }
    
    /// "name" variable: type
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