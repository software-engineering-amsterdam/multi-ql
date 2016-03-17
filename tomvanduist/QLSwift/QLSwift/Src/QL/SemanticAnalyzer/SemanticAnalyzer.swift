//
//  SemanticAnalyzer.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation


class SemanticAnalyzer {
    
    func analyze(form: QLForm) throws -> [SemanticWarning] {
        return try analyze(form,
            rules: [
                TypeChecker(),
                ScopeChecker(),
                CyclicDependencyChecker()
            ])
    }
    
    internal func analyze(form: QLForm, rules: [SemanticAnalysisRule]) throws -> [SemanticWarning] {
        let context = try Context(form: form)
        
        let result = rules.reduce(SemanticAnalysisResult(), combine: { result, rule in
            result.combine(rule.run(form, context: context))
        })
        
        
        if result.didSucceed() {
            return result.getWarnings()
        }
        
        throw SemanticErrorCollection(errors: result.getErrors())
    }
}