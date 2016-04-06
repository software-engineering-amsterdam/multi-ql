//
//  CyclicDependencyChecker.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation


internal class CyclicDependencyChecker: SemanticAnalysisRule, TopDown {
    private var context: Context!
    private var questionMap = Map<QLQuestion>()
    
    func run(form: QLForm, context: Context) -> SemanticAnalysisResult {
        self.context = context
        self.questionMap = QuestionMapFiller().fill(form, map: Map<QLQuestion>())
        
        let errors = checkCyclicDependencies(form)
        
        return SemanticAnalysisResult(success: errors.isEmpty, warnings: [], errors: errors)
    }
    
    private func checkCyclicDependencies(form: QLForm) -> [SemanticError] {
        return self.visit(form.block, param: [])
    }
}


// MARK: - QLStatementVisitor

extension CyclicDependencyChecker {
    
    func visit(node: QLComputedQuestion, param: [String]) -> [SemanticError] {
        var identifiers = param
        identifiers.append(node.identifier.id)
        
        return node.expression.accept(self, param: identifiers)
    }
    
    func visit(node: QLBlock, param: [String]) -> [SemanticError] {
        var errors: [SemanticError] = []
        
        for statement in node.block {
            errors += statement.accept(self, param: param)
        }
        
        return errors
    }
    
    func defaultLeafResult(statement: QLStatement?, param: [String]) -> [SemanticError] {
        return []
    }
}


// MARK: - QLExpressionVisitor

extension CyclicDependencyChecker {
    
    func visit(node: QLVariable, param: [String]) -> [SemanticError] {
        guard let question = questionMap.retrieve(node.id)
            else { return [] }
        
        let current = question.identifier.id
        
        if introducesCycle(current, identifiers: param) {
            return [CyclomaticDependencyError(description: "A cyclomatic dependency exists for the identifier \(current). Path: \(constructPath(param + [current]))")]
        } else {
            return question.accept(self, param: param)
        }
    }

    func visitBinary(binary: QLBinary, param identifiers: [String]) -> [SemanticError] {
        let lhsErrors = binary.lhs.accept(self, param: identifiers)
        let rhsErrors = binary.rhs.accept(self, param: identifiers)
        
        return lhsErrors + rhsErrors
    }
    
    func defaultLeafResult(expression: QLExpression, param: [String]) -> [SemanticError] {
        return []
    }
}


// MARK: - QLLiteralVisitor

extension CyclicDependencyChecker {
    
    func defaultLeafResult(literal: QLLiteral, param: [String]) -> [SemanticError] {
        return []
    }
}


// MARK: - QLTypeVisitor

extension CyclicDependencyChecker {
    
    func defaultLeafResult(type: QLType, param: [String]) -> [SemanticError] {
        return []
    }
}


// MARK: - Private methods

extension CyclicDependencyChecker {
    
    private func introducesCycle(current: String, identifiers: [String]) -> Bool {
        return identifiers.indexOf(current) != nil
    }
    
    private func constructPath(identifiers: [String]) -> String {
        var path: String = ""
        for i in 0...identifiers.count-1 {
            path += identifiers[i]
            if i != identifiers.count-1 {
                path += " -> "
            }
        }
        return path
    }
    
    private func asSemanticError(error: SemanticError) -> SemanticError {
        return error
    }
    
    private func asSemanticError(error: ErrorType) -> SemanticError {
        return SystemError(error: error)
    }
}


private class QuestionMapFiller: TopDownStatement {
    
    func fill(form: QLForm, map: Map<QLQuestion>) -> Map<QLQuestion> {
        return form.block.accept(self, param: map)
    }
    
    func visit(node: QLVariableQuestion, param map: Map<QLQuestion>) -> Map<QLQuestion> {
        map.assign(node.identifier.id, value: node)
        
        return map
    }
    func visit(node: QLComputedQuestion, param map: Map<QLQuestion>) -> Map<QLQuestion> {
        map.assign(node.identifier.id, value: node)
        
        return map
    }
    func visit(node: QLBlock, param map: Map<QLQuestion>) -> Map<QLQuestion> {
        var newMap = map
        
        for statement in node.block {
            newMap = statement.accept(self, param: map)
        }
        
        return newMap
    }
    
    func defaultLeafResult(statement: QLStatement?, param map: Map<QLQuestion>) -> Map<QLQuestion> {
        return map
    }
}