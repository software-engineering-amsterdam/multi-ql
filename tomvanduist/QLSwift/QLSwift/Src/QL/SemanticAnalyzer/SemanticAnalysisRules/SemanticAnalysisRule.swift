//
//  SemanticAnalysisRule.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation

protocol SemanticAnalysisRule {
    func run(form: QLForm, context: Context) -> SemanticAnalysisResult
}


class SemanticAnalysisResult {
    private var success: Bool
    private var errors: [SemanticError]
    private var warnings: [SemanticWarning]
    
    init() {
        self.success = true
        self.warnings = []
        self.errors = []
    }
    
    init(success: Bool, warnings: [SemanticWarning], errors: [SemanticError]) {
        self.success = success
        self.warnings = warnings
        self.errors = errors
    }
    
    func didSucceed() -> Bool {
        return success
    }
    
    func hasWarnings() -> Bool {
        return !warnings.isEmpty
    }
    
    func hasErrors() -> Bool {
        return !errors.isEmpty
    }
    
    func getWarnings() -> [SemanticWarning] {
        return warnings
    }
    
    func getErrors() -> [SemanticError] {
        return errors
    }
    
    func combine(otherResult: SemanticAnalysisResult) -> SemanticAnalysisResult {
        success = success && otherResult.success
        warnings += otherResult.warnings
        errors += otherResult.errors
        
        return self
    }
    
    internal func collectWarning(warning: SemanticWarning) {
        self.warnings.append(warning)
    }
    
    internal func collectError(error: SemanticError) {
        success = false
        errors.append(error)
    }
    
    internal func collectError(error: ErrorType) {
        success = false
        errors.append(SystemError(error: error))
    }
}