//
//  QLParser.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation
import SwiftParsec

class QLParser: NSObject {
    
    func parseStream(data: String) throws -> QLForm {
        return try parser().run(sourceName: "", input: data)
    }
    
    func parser() -> GenericParser<String, (), QLForm> {
        
        let ql = LanguageDefinition<()>.ql
        let lexer = GenericTokenParser(languageDefinition: ql)
        
        let symbol = lexer.symbol
        //        let stringLiteral = lexer.stringLiteral
        //
        //        let noneOf = StringParser.noneOf
        
        // String.
        
        //        let temp1 = lexer.identifier.map{ (id) -> QLForm in
        //            print(id)
        //            return QLForm(form: id)
        //        }
        
        let string = lexer.identifier.map{ (str) -> QLString in
            print("String: \(str)")
            return QLString(string: str)
        }
        
        let strings: GenericParser<String, (), [QLString]> = string.manyAccumulator{ (let current: QLString, var accumulated: [QLString]) in
            accumulated.append(current) // Doesn't return an instance of itself.
            return accumulated
        }
        
        
        let codeBlock = lexer.braces(strings).map{ (strs) -> QLCodeBlock in
            print("In codeBlock \(strs)")
            return QLCodeBlock(codeBlock: strs)
        }
        
        
        //        let formName = lexer.identifier.map{ (formName) -> String in
        ////            print(formName)
        //            return formName
        //        }
        
        //        let temp2 = lexer.identifier.map{ (formName) -> QLForm in
        //            print(formName)
        //            return QLForm(formName: formName)
        //        }
        
        // Form.
        let form = symbol("form") *> string.flatMap{ formName in
            
            return codeBlock.map { block in
                QLForm(formName: formName, codeBlocks: block)
            }
        }
        
        return lexer.whiteSpace *> form
        
    }
    
}