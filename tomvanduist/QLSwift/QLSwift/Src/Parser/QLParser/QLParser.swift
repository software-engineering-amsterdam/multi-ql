//
//  QLParser.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation
import SwiftParsec

//public enum QLIdentifier {
//    case String
//}

//public enum QLStatement {
//    case Expression
//    indirect case StatementList(QLStatement)
//}

//public indirect enum QLForm {
//    
//    public enum QLStatement {
//        case Expression
//        indirect case StatementList(QLStatement)
//    }
//    
//    case QLForm(identifier: String, statement: QLStatement)
//    case QLIdentifier(String)
//}
//
//
//class QLTest: NSObject {
//    
//    init(s: String) {
//        print(s)
//    }
//}

class QLParser: NSObject {
    
    

    
    func testQL() {
        qlParser().test(try! String(stringFromFile: "QL", ofType: "txt"))
        if let tmp = try? qlParser().run(sourceName: "", input: String(stringFromFile: "QL", ofType: "txt")) {
            print(tmp)
        }
    }
    
    private func qlParser() -> GenericParser<String, (), QLStatement> {
        
        let lexer           = GenericTokenParser(languageDefinition: LanguageDefinition<()>.ql)
        let symbol          = lexer.symbol
        let integerLiteral  = lexer.integer
        let floatLiteral    = lexer.float
        let stringLiteral   = lexer.stringLiteral
        let identifier      = lexer.identifier
        let colon           = symbol(":")
        
        
        let qlString: GenericParser<String, (), QLExpression> =
            lexer.stringLiteral.map { QLString(value: $0) }
        let qlBooleanValue: GenericParser<String, (), QLExpression> =
            lexer.symbol("true") *> GenericParser(result: QLBooleanValue(value: true)) <|>
            lexer.symbol("false") *> GenericParser(result: QLBooleanValue(value: false))
        
        var qlExpression: GenericParser<String, (), QLExpression>!

        // Expression recursive definition
        GenericParser.recursive { (expr: GenericParser<String, (), QLExpression>) in
            let boolExpr: GenericParser<String, (), QLExpression> =
                symbol("boolean") *> GenericParser(result: QLBoolean()) <|>
                qlBooleanValue
            let moneyExpr: GenericParser<String, (), QLExpression> =
                symbol("money") *> lexer.parentheses(expr).map { QLMoney(expr: $0) }.attempt <|>
                symbol("money") *> GenericParser(result: QLMoney())
            
            qlExpression = boolExpr <|> moneyExpr
            
            return qlExpression
        }
        
        
        var qlStatement: GenericParser<String, (), QLStatement>!
        
        // Statement recursive definition
        GenericParser.recursive { (stmt: GenericParser<String, (), QLStatement>) in
            let qlStatementList: GenericParser<String, (), QLStatement>
                = lexer.braces(stmt).map { QLStatementList(statements: $0) }
            
            
            return qlStatement
        }
        
        
        return lexer.whiteSpace *> qlStatement
    }
    
    
    
    // MARK: CSV test
    
    func testCSV() {
        csvParser().test("Blade Runner, Rutger Hauer")
    }

    private func csvParser() -> GenericParser<String, (), [[String]]> {
        let noneOf = StringParser.noneOf
        
        let quotedChars = noneOf("\"") <|>
            StringParser.string("\"\"").attempt *>
            GenericParser(result: "\"")
        
        let character = StringParser.character
        
        let quote = character("\"")
        let quotedField = quote *> quotedChars.many.stringValue <*
            (quote <?> "quote at end of field")
        
        let field = quotedField <|> noneOf("\r\n,\n\r").many.stringValue
        let record = field.separatedBy(character(","))
        
        let endOfLine = StringParser.crlf.attempt <|>
            (character("\n") *> character("\r")).attempt <|>
            character("\n") <|>
            character("\r") <?> "end of line"
        
        return record.separatedBy(endOfLine)
    }
    
    
    // MARK: JSON test
    
    func testJSON() {
        JSONValue.parser.test("{ \"Movie\": \"Blade Runner\", \"Cast\": [\"Rutger Hauer\", \"Harrison Ford\", true ]}")
    }
}

public enum JSONValue {
    
    case JString(String)
    case JNumber(Double)
    case JBool(Bool)
    case JNull
    case JArray([JSONValue])
    case JObject([String: JSONValue])
    case Error
    
    public static let parser: GenericParser<String, (), JSONValue> = {
        
        let json = LanguageDefinition<()>.json
        let lexer = GenericTokenParser(languageDefinition: json)
        
        let symbol = lexer.symbol
        let stringLiteral = lexer.stringLiteral
        
        let jstring = JSONValue.JString <^> stringLiteral
        let jnumber = JSONValue.JNumber <^>
            (lexer.float.attempt <|> lexer.integerAsFloat)
        
        let trueValue = symbol("true") *> GenericParser(result: true)
        let falseValue = symbol("false") *> GenericParser(result: false)
        let jbool = JSONValue.JBool <^> (trueValue <|> falseValue)
        
        let jnull = symbol("null") *> GenericParser(result: JSONValue.JNull)
        
        var jarray: GenericParser<String, (), JSONValue>!
        var jobject: GenericParser<String, (), JSONValue>!
        
        GenericParser.recursive { (jvalue: GenericParser<String, (), JSONValue>) in
            
            let jarrayValues = lexer.commaSeparated(jvalue)
            jarray = JSONValue.JArray <^> lexer.brackets(jarrayValues)
            
            let nameValue: GenericParser<String, (), (String, JSONValue)> =
            stringLiteral >>- { name in
                
                symbol(":") *> jvalue.map { value in (name, value) }
            }
            
            let dictionary: GenericParser<String, (), [String: JSONValue]> =
            (symbol(",") *> nameValue).manyAccumulator { (assoc, var dict) in
                
                let (name, value) = assoc
                dict[name] = value
                
                return dict
            }
            
            let jobjectDict: GenericParser<String, (), [String: JSONValue]> =
            nameValue >>- { assoc in
                
                dictionary >>- { (var dict) in
                    
                    let (name, value) = assoc
                    dict[name] = value
                    
                    return GenericParser(result: dict)
                }
            }
            
            let jobjectValues = jobjectDict <|> GenericParser(result: [:])
            jobject = JSONValue.JObject <^> lexer.braces(jobjectValues)
            
            return jstring <|> jnumber <|> jbool <|> jnull <|> jarray <|> jobject
        }
        
        return lexer.whiteSpace *> (jobject <|> jarray)
        
    }()
    
    public init(data: String) throws {
        
        try self = JSONValue.parser.run(sourceName: "", input: data)
        
    }
    
    public var string: String? {
        
        guard case .JString(let str) = self else { return nil }
        
        return str
        
    }
    
    public var double: Double? {
        
        guard case .JNumber(let dbl) = self else { return nil }
        
        return dbl
        
    }
    
    public var bool: Bool? {
        
        guard case .JBool(let b) = self else { return nil }
        
        return b
        
    }
    
    public var isNull: Bool {
        
        if case .JNull = self { return true }
        
        return false
        
    }
    
    public subscript(name: String) -> JSONValue {
        
        guard case .JObject(let dict) = self,
            let value = dict[name] else { return .Error }
        
        return value
        
    }
    
    public subscript(index: Int) -> JSONValue {
        
        guard case .JArray(let arr) = self where
            index >= 0 && index < arr.count else { return .Error }
        
        return arr[index]
        
    }
    
}