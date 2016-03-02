//
//  QLParser.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation
import SwiftParsec

// Parsec to Swift
// <^> .map
// >>- .flatMap

class QLParser: NSObject {
    
    func parseStream(data: String) throws -> QLForm {
        return try parser().run(sourceName: "", input: data)
    }
    
    func parser() -> GenericParser<String, (), QLForm> {
        
        let ql = LanguageDefinition<()>.ql
        let lexer = GenericTokenParser(languageDefinition: ql)
        
        let symbol = lexer.symbol
        let noneOf = StringParser.noneOf
        let oneOf = StringParser.oneOf
        let character = StringParser.character
        let stringLiteral = lexer.stringLiteral
        let eof = StringParser.eof
        
        let endOfLine = StringParser.crlf.attempt <|>
            (character("\n") *> character("\r")).attempt <|>
            character("\n") <|>
            character("\r") <?> "End of line."
        
        // Strings.
        let qlstring = lexer.identifier.map{ (str) -> QLString in
            print("String: \(str)")
            return QLString(string: str)
        }
        
        let qlstrings = qlstring.manyAccumulator{ (let current: QLString, var accumulated: [QLString]) in
            accumulated.append(current) // Doesn't return an instance of itself.
            return accumulated
        }
        
        // MARK: Questions. From CSV example code. Added end of line.
        let quotedChars = noneOf("\"") <|> StringParser.string("\"\"").attempt *> GenericParser(result: "\"")
        let quote = character("\"")
        
        
//        "Did you sell a house in 2010?"
    //        hasSoldHouse: boolean
        
        let qlquestionName = quote *> quotedChars.many.stringValue <* quote <* endOfLine <?> "Quote/endOfLine at end of question name."
        let qlquestionVariable = lexer.whiteSpace *> lexer.identifier.map{ String($0) } <* lexer.colon <* symbol("boolean")
        
        let qlquestion: GenericParser<String, (), QLQuestion> = qlquestionName.flatMap{ questionName in
            
            qlquestionVariable.map{ questionVariable -> QLQuestion in
                
                QLQuestion(name: questionName, variable: questionVariable, type: "boolean")

            }
            
            
        }
        
        
//        qlstring.flatMap{ (variableName) in
//            print("Variable name: \(variableName)")
//            
//            let temp = (lexer.colon *> symbol("boolean")).map{ (variableType) in
//                print("Question type: \(variableType)")
//                return QLQuestion(name: qlquestionName, variable: variableName, type: variableType)
//            }
//            
//            print("Temp: \(temp)")
//            
//            return temp
//        }
//            <*> lexer.colon <*> symbol("boolean")
//        let qlquestion = qlquestionName <*> qlQuestionVariable
        
//       let qlquestionName = quote *> quotedChars.many.stringValue.map{ name in QLQuestion(name: question) } <* quote <* endOfLine <?> "quote at end of field"
    
        // MARK: Statements.
        let qlstatement = qlquestion
        
        let qlstatements: GenericParser<String, (), [QLStatement]> = qlstatement.manyAccumulator { (let statement, var accumulated) in
            print("Statement: \(statement)")
            accumulated.append(statement)
            return accumulated
        }
        
        let codeBlock: GenericParser<String, (), [QLStatement]> = lexer.braces(qlstatements) <?> "Error parsing in the braces of code block."
        
        let form = symbol("form") *> qlstring.flatMap{ (formName) -> GenericParser<String, (), QLForm> in
            
            print("Form name: \(formName)")
            
            let temp = codeBlock.map{ (let block: [QLStatement]) -> QLForm in
                print("Block: \(block)")
                return QLForm(formName: formName, codeBlock: block)
            }
            
            print("Temp: \(temp)")
            
            return temp
        }  <?> "Error at the end of the form."
        
        return lexer.whiteSpace *> form
        
    }
    
}