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
    func parse(ql: QL) throws -> (QLForm, [SemanticWarning]) {
        let form = try QLParser().parse(ql)
        let warnings = try SemanticAnalyzer().analyze(form)
        
        return (form, warnings)
    }
}