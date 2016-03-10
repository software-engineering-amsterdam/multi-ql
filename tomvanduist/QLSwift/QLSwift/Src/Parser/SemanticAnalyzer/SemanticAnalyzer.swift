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
        
        // Infer types
        let typeInferer = TypeInferer()
        let symbolTable = try typeInferer.inferTypes(form)
        
        // Perform remaining rules
        var results: [SemanticAnalysisResult] = []
        let semanticRules: [SemanticAnalysisRule] = [
            TypeChecker(),
            ScopeChecker(),
            CyclicDependencyChecker()
        ]
        
        for rule in semanticRules {
            results.append(rule.run(form, symbolTable: symbolTable))
        }
        
        let errors = retrieveErrors(results)
        if !errors.isEmpty {
            throw SemanticErrorCollection(errors: errors)
        }
        
        return retrieveWarnings(results)
    }
    
    private func retrieveErrors(semanticResults: [SemanticAnalysisResult]) -> [SemanticError] {
        var errors: [SemanticError] = []
        
        semanticResults.forEach { errors += $0.errors }
        
        return errors
    }
    
    private func retrieveWarnings(semanticResults: [SemanticAnalysisResult]) -> [SemanticWarning] {
        var warnings: [SemanticWarning] = []
        
        semanticResults.forEach { warnings += $0.warnings }
        
        return warnings
    }
}
