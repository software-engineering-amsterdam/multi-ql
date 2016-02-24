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
        let qlParser = QLParser()
        
        let parseTree = try qlParser.parse(ql)
        let form = parseTree.implode()
        
        let sa = SemanticAnalyser(context: Context.sharedInstance)
        try sa.analyze(form)
        
        return form
    }
}