//
//  SemanticAnalysisRule.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation

struct SemanticAnalysisResult {
    let success: Bool
    let errors: [SemanticError]
    let warnings: [SemanticWarning]
    
    init(success: Bool, warnings: [SemanticWarning], errors: [SemanticError]) {
        self.success = success
        self.warnings = warnings
        self.errors = errors
    }
}

protocol SemanticAnalysisRule {
    func run(form: QLForm, symbolTable: SymbolTable) -> SemanticAnalysisResult
}