//
//  SemanticAnalysisRule.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation

struct SemanticAnalysisResult<T> {
    let generic: T
    let errors: [SemanticError]
    let warnings: [SemanticWarning]
    
    init(generic: T, warnings: [SemanticWarning], errors: [SemanticError]) {
        self.generic = generic
        self.warnings = warnings
        self.errors = errors
    }
}

protocol SemanticAnalysisRule {
    typealias GenericParam
    typealias GenericResult
    
    func run(param: GenericParam) -> SemanticAnalysisResult<GenericResult>
}