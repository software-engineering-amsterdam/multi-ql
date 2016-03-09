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
        let typeInferer = TypeInferer()
        let typeChecker = TypeChecker()
        let scopeChecker = ScopeChecker()
        let cdChecker = CyclicDependencyChecker()
        
        
        let tiResult = typeInferer.run(form)
        let symbolTable = tiResult.generic
        
        var results: [SemanticAnalysisResult<Bool>] = [SemanticAnalysisResult(generic: true, warnings: tiResult.warnings, errors: tiResult.errors)]
        results.append(typeChecker.run((form: form, symbolTable: symbolTable)))
        results.append(scopeChecker.run((form: form, symbolTable: symbolTable)))
        results.append(cdChecker.run((form: form, symbolTable: symbolTable)))
        
        
        let errors = retrieveErrors(results)
        if !errors.isEmpty {
            throw SemanticErrorCollection(errors: errors)
        }
        
        return retrieveWarnings(results)
    }
    
    private func retrieveErrors<T>(semanticResults: [SemanticAnalysisResult<T>]) -> [SemanticError] {
        var errors: [SemanticError] = []
        
        semanticResults.forEach { errors += $0.errors }
        
        return errors
    }
    
    private func retrieveWarnings<T>(semanticResults: [SemanticAnalysisResult<T>]) -> [SemanticWarning] {
        var warnings: [SemanticWarning] = []
        
        semanticResults.forEach { warnings += $0.warnings }
        
        return warnings
    }
}
