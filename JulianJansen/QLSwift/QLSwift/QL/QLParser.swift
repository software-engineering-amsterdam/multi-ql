//
//  QLParser.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//
//  Parsec to Swift
//  <^>  ->  .map
//  >>-  ->  .flatMap

import Foundation
import SwiftParsec

class QLParser {
    private let lexer = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
    private let symbol = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).symbol
    private let integerLiteral = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).integer
    private let floatLiteral = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).float
    private let stringLiteral = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).stringLiteral
    private let identifier = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).identifier
    private let colon = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).colon
    private let whiteSpace = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).whiteSpace

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

    // MARK: Literals and variables
    
    private func booleanFalseParser() -> GenericParser<String, (), QLExpression> {
        return symbol("false") *> GenericParser(result: QLBoolLiteral(boolean: false))
    }

    private func booleanTrueParser() -> GenericParser<String, (), QLExpression> {
        return symbol("true") *> GenericParser(result: QLBoolLiteral(boolean: true))
    }

    private func stringParser() -> GenericParser<String, (), QLExpression> {
        return stringLiteral.map{ QLStringLiteral(string: $0) }
    }
    
    private func integerParser() -> GenericParser<String, (), QLExpression> {
        return integerLiteral.map{ QLIntegerLiteral(integer: $0) }
    }
    
    private func literalParser() -> GenericParser<String, (), QLExpression> {
        return booleanFalseParser() <|> booleanTrueParser() <|> stringParser() <|> integerParser()
    }
    
    private func variableParser() -> GenericParser<String, (), QLExpression> {
        return identifier.map{ QLVariable(name: $0) }
    }
    
    // MARK: Expressions
    
    private func expressionParser() -> GenericParser<String, (), QLExpression> {
        
        let opTable: OperatorTable<String, (), QLExpression> = [
            
            [
                prefix("!", function: { QLNotExpression(expression: $0) })
            ],
            [
                binary("*", function: { QLMultiplyExpression(lhs: $0, rhs: $1) }, assoc: .Left),
                binary("/", function: { QLDivideExpression(lhs: $0, rhs: $1) }, assoc: .Left)
            ],
            [
                binary("+", function: { QLAddExpression(lhs: $0, rhs: $1) }, assoc: .Left),
                binary("-", function: { QLSubtractExpression(lhs: $0, rhs: $1) }, assoc: .Left)
            ],
            [
                binary("!=", function: { QLIsNotExpression(lhs: $0, rhs: $1) }, assoc: .None),
                binary("==", function: { QLIsExpression(lhs: $0, rhs: $1) }, assoc: .None),
                binary((">=", function: { QLGreaterOrIsExpression(lhs: $0, rhs: $1) } ),
                    doubleChar: (">", function: { QLGreaterThanExpression(lhs: $0, rhs: $1) } ),
                    assoc: .None),
                binary(("<=", function: { QLSmallerOrIsExpression(lhs: $0, rhs: $1) } ),
                    doubleChar: ("<", function: { QLSmallerThanExpression(lhs: $0, rhs: $1) } ),
                    assoc: .None)
            ],
            [
                binary("||", function: { QLOrExpression(lhs: $0, rhs: $1) }, assoc: .Left),
                binary("&&", function: { QLAndExpression(lhs: $0, rhs: $1) }, assoc: .Left)
            ]
        ]
        
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        
        return opTable.expressionParser { expression in
            
            expression.between(openingParen, closingParen) <|> literalParser() <|> variableParser() <?> "nested expression, literal or variable"
        
        } <?> "expression"
        
    }
    
    private func ifParser() -> GenericParser<String, (), QLStatement> {
        return (symbol("if") *> self.whiteSpace *> expressionParser()).flatMap{ condition in
            (self.whiteSpace *> self.codeBlockParser()).map { codeBlock in
                QLIfStatement(condition: condition, codeBlock: codeBlock)
            }
        }
    }
    
    private func getQuestionType(type: String) -> QLType {
        switch type {
            case "boolean":
                return QLBoolType()
            case "string":
                return QLStringType()
            case "integer":
                return QLIntegerType()
            default:
                return QLUnknownType()
        }
    }
    
    private func questionParser() -> GenericParser<String, (), QLStatement> {
        return (stringLiteral <?> "question name").flatMap{ label in
            (self.identifier <* self.colon <?> "question variable").flatMap{ name -> GenericParser<String, (), QLStatement> in
                (StringParser.noneOf("\r\n,\n\r").many.stringValue <* self.endOfLineParser() <* self.whiteSpace <?> "type identifier").map{ type in
                    return QLQuestion(name: name, label: label, type: self.getQuestionType(type))
                }
            }
        }
    }
    
    private func computedQuestionParser() -> GenericParser<String, (), QLStatement> {
        return (stringLiteral <?> "computed question name").flatMap{ label in
            (self.identifier <* self.colon <* self.whiteSpace <?> "question variable").flatMap{ name -> GenericParser<String, (), QLStatement> in
                (StringParser.noneOf("\r\n,\n\r").many.stringValue <* self.endOfLineParser() <* self.whiteSpace <?> "type identifier").flatMap{ type in
                    (self.symbol("=") *> self.whiteSpace *> self.expressionParser() <* self.whiteSpace <?> "expression").map{ expression in
                        return QLQuestion(name: name, label: label, type: self.getQuestionType(type), expression: expression)
                    }
                }
            }
        }
    }

    private func codeBlockParser() -> GenericParser<String, (), [QLStatement]> {
        let qlstatement = computedQuestionParser().attempt <|> questionParser() <|> ifParser()

        let qlstatements: GenericParser<String, (), [QLStatement]> = qlstatement.manyAccumulator { (statement, accumulated) in
            var temp = accumulated
            temp.append(statement)
            return temp
        }

        return lexer.braces(qlstatements)
    }
    
    private func formParser() -> GenericParser<String, (), QLForm> {
        return symbol("form") *> identifier.flatMap{ formName in
            self.codeBlockParser().map{ block in
                return QLForm(formName: formName, codeBlock: block)
            }
        } <?> "form"
    }
    
    // MARK: Expression operator functions
    
    private func binary(name: String, function: (QLExpression, QLExpression) -> QLExpression, assoc: Associativity) -> Operator<String, (), QLExpression> {
        let opParser =  StringParser.string(name) *> self.whiteSpace *> GenericParser(result: function)
        return .Infix(opParser, assoc)
    }
    
    // Fix for > and >= difference. Idea for >= and > in one call by Tom van Duist.
    private func    binary(singleChar: (name: String, function: (QLExpression, QLExpression) -> QLExpression),
                    doubleChar: (name: String, function: (QLExpression, QLExpression) -> QLExpression),
                    assoc: Associativity) -> Operator<String, (), QLExpression> {
        
        let opParser =
            (StringParser.string(singleChar.name) *> self.whiteSpace *> GenericParser(result: singleChar.function)).attempt
            <|>
            (StringParser.string(doubleChar.name) *> self.whiteSpace *> GenericParser(result: doubleChar.function)) <?> "single or double char binary operator"
        
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