//
//  CyclicDependencyChecker.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation


internal class CyclicDependencyChecker: SemanticAnalysisRule, QLNodeVisitor {
    
    typealias QLStatementVisitorParam   = [String]
    typealias QLExpressionVisitorParam  = [String]
    typealias QLLiteralVisitorParam     = [String]
    typealias QLTypeVisitorParam        = [String]
    typealias QLStatementVisitorReturn  = [SemanticError]
    typealias QLExpressionVisitorReturn = [SemanticError]
    typealias QLLiteralVisitorReturn    = [SemanticError]
    typealias QLTypeVisitorReturn       = [SemanticError]
    
    private var symbolTable: SymbolTable = SymbolTable()
    
    
    func run(form: QLForm, symbolTable: SymbolTable) -> SemanticAnalysisResult {
        resetInternals(symbolTable)
        
        let errors = checkCyclicDependencies(form)
        
        return SemanticAnalysisResult(success: errors.isEmpty, warnings: [], errors: errors)
    }
    
    private func checkCyclicDependencies(form: QLForm) -> [SemanticError] {
        return self.visit(form.block, param: [])
    }
}


// MARK: - QLStatementVisitor conformance

extension CyclicDependencyChecker {
    
    func visit(node: QLVariableQuestion, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLComputedQuestion, param: [String]) -> [SemanticError] {
        var identifiers = param
        identifiers.append(node.identifier.id)
        
        return node.expression.accept(self, param: identifiers)
    }
    
    func visit(node: QLConditional, param: [String]) -> [SemanticError] {
        return node.ifBlock.accept(self, param: param)
    }
    
    func visit(node: QLBlock, param: [String]) -> [SemanticError] {
        var errors: [SemanticError] = []
        
        for statement in node.block {
            errors += statement.accept(self, param: param)
        }
        
        return errors
    }
}


// MARK: - QLExpressionVisitor conformance

extension CyclicDependencyChecker {
    
    func visit(node: QLVariable, param: [String]) -> [SemanticError] {
        guard let question = symbolTable.retrieveQuestion(node.id)
            else { return [] }
        
        let current = question.identifier.id
        
        if introducesCycle(current, identifiers: param) {
            return [CyclomaticDependencyError(description: "A cyclomatic dependency exists for the identifier \(current). Path: \(constructPath(param + [current]))")]
        } else {
            return question.accept(self, param: param)
        }
    }
    
    func visit(node: QLLiteralExpression, param: [String]) -> [SemanticError] {
        return node.literal.accept(self, param: param)
    }
    
    func visitUnary(unary: QLUnary, identifiers: [String]) -> [SemanticError] {
        return unary.rhs.accept(self, param: identifiers)
    }
    
    func visit(node: QLNeg, param: [String]) -> [SemanticError] {
        return visitUnary(node, identifiers: param)
    }
    
    func visit(node: QLNot, param: [String]) -> [SemanticError] {
        return visitUnary(node, identifiers: param)
    }
    
    func visitBinary(binary: QLBinary, identifiers: [String]) -> [SemanticError] {
        let lhs = binary.lhs.accept(self, param: identifiers)
        let rhs = binary.rhs.accept(self, param: identifiers)
        
        return lhs + rhs
    }
    
    func visit(node: QLAdd, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLSub, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLMul, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLDiv, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLPow, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLGe, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLGt, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLLe, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLLt, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLEq, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLNe, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLAnd, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLOr, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
}


// MARK: - QLLiteralVisitor conformance

extension CyclicDependencyChecker {
    
    func visit(node: QLIntegerLiteral, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLStringLiteral, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLBooleanLiteral, param: [String]) -> [SemanticError] {
        return []
    }
}


// MARK: - QLTypeVisitor conformance

extension CyclicDependencyChecker {
    
    func visit(node: QLIntegerType, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLStringType, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLBooleanType, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLVoidType, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLUnknownType, param: [String]) -> [SemanticError] {
        return []
    }
}


// MARK: - Private methods

extension CyclicDependencyChecker {
    
    private func resetInternals(symbolTable: SymbolTable) {
        self.symbolTable = symbolTable
    }
    
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