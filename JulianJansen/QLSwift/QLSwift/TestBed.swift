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
//    
//    func parseStream(data: String) throws -> QLExpression {
//        return try testExpressions().run(sourceName: "", input: data)
//    }
    
//    private func testExpressions() -> GenericParser<String, (), QLExpression> {
//        
//
//        
//        
//        
//        
//        
//
//        
//        let openingParen = StringParser.character("(")
//        let closingParen = StringParser.character(")")
//        
//        
//
//        
//        
//        return qlif
//    }
    

}

