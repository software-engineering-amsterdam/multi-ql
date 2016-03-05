//
//  TestBed.swift
//  QLSwift
//
//  Created by Julian Jansen on 05-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation
import SwiftParsec

class TestBed {
    
    let symbol = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).symbol
    let stringLiteral = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).stringLiteral // Includes the quotes.
    let identifier = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).identifier
    let colon = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql).colon
    
    func parseStream(data: String) throws -> QLExpression {
        return try testExpressions().run(sourceName: "", input: data)
    }
    
    private func testExpressions() -> GenericParser<String, (), QLExpression> {
        
        let opTable: OperatorTable<String, (), QLExpression> = [
            
            [
                binary("&&", function: andOperator, assoc: .Left)
            ]
            
        ]
        
        let openingParen = StringParser.character("(")
        let closingParen = StringParser.character(")")
        let decimal = GenericTokenParser<()>.decimal
        
        let qlexpression: GenericParser<String, (), QLExpression> = opTable.expressionParser { expression in
            
            expression.between(openingParen, closingParen) <|> qlexpression <|>  <?> "expression"
        
        } <?> "expression"
        
        let qlif = symbol("if") *> qlexpression
        
        return qlif
    }
    
    // MARK: Expression operators.
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

