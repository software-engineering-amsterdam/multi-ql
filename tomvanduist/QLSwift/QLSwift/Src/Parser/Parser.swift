//
//  Parser.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

typealias QL = String

extension QL {
    init(qlFromFileNamed file: String) throws {
        try self.init(stringFromFile: file, ofType: "ql")
    }
}

class Parser {
    func parse(ql: QL) throws -> Form {
//        let qlParser = QLParser()
//        
//        let form = try qlParser.parse(ql)
//        
//        let sa = SemanticAnalyser(context: Context.sharedInstance)
//        
//        return try sa.analyze(form) as! Form
        
        
        
        let qlParser = QLParser()
        
        
        
//        let expr = try qlParser.expr().run(sourceName: "QL", input: QL(qlFromFileNamed: "EvalAdd"))
//        print(expr)

        
//        let expr = try qlParser.question().run(sourceName: "QL", input: QL(qlFromFileNamed: "EvalAdd"))
//        print(expr)
        
        
//        let expr = try qlParser.ifStatement().run(sourceName: "QL", input: QL(qlFromFileNamed: "EvalAdd"))
//        print(expr)
        
        
        let expr = try qlParser.form().run(sourceName: "QL", input: QL(qlFromFileNamed: "EvalAdd"))
        print(expr)
        


        
        
//        let sa = SemanticAnalyser(context: Context.sharedInstance)
        
//        return try sa.analyze(form) as! Form
        return Form(identifier: Identifier(id: ""), block: Block(block: []))
    }
}