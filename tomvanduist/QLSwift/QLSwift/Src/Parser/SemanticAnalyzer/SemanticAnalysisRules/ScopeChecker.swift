//
//  ScopeChecker.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation


private class ScopedSymbolTable: SymbolTable {
    let parent: ScopedSymbolTable?
    
    init(parent: ScopedSymbolTable? = nil) {
        self.parent = parent
    }
    
    override func retrieve(identifier: String) -> Symbol? {
        if let o = super.retrieve(identifier) {
            return o
        }
        
        return parent?.retrieve(identifier)
    }
}


internal class ScopeChecker: SemanticAnalysisRule, QLStatementVisitor, QLExpressionVisitor {
    
    typealias QLStatementVisitorParam   = Void?
    typealias QLExpressionVisitorParam  = Void?
    typealias QLStatementVisitorReturn  = Void
    typealias QLExpressionVisitorReturn = Void
    
    private var scopedSymbolTable: ScopedSymbolTable = ScopedSymbolTable(parent: nil)
    private var symbolTable: SymbolTable!
    private var errors: [SemanticError] = []
    private var warnings: [SemanticWarning] = []
    
    
    func run(form: QLForm, symbolTable: SymbolTable) -> SemanticAnalysisResult {
        resetInternals(symbolTable)
        
        checkScopes(form)
        
        return SemanticAnalysisResult(success: errors.isEmpty, warnings: warnings, errors: errors)
    }
}


// MARK: - QLStatementVisitor conformance

extension ScopeChecker {
    
    func visit(node: QLVariableQuestion, param: Void?) {
        // no-op
    }
    
    func visit(node: QLComputedQuestion, param: Void?) {
        node.expression.accept(self, param: nil)
    }
    
    func visit(node: QLConditional, param: Void?) {
        node.condition.accept(self, param: param)
        node.ifBlock.accept(self, param: param)
    }
    
    func visit(node: QLBlock, param: Void?) {
        scopedSymbolTable = ScopedSymbolTable(parent: scopedSymbolTable)
        
        // Assign scopes
        for question in node.questions() {
            assignScope(question)
        }
        
        // Visit questions
        for question in node.questions() {
            question.accept(self, param: param)
        }
        
        // Visit deeper scopes
        for conditional in node.conditionals() {
            conditional.accept(self, param: param)
        }
        
        if let parentScope = scopedSymbolTable.parent {
            scopedSymbolTable = parentScope
        }
    }
}


// MARK: - QLExpressionVisitor conformance

extension ScopeChecker {
    
    func visit(node: QLVariable, param: Void?) {
        return retrieveSymbolType(node.id)
    }
    
    func visit(node: QLLiteralExpression, param: Void?) {
    }
    
    func visitUnary(unary: QLUnary) {
        unary.rhs.accept(self, param: nil)
    }
    
    func visit(node: QLNeg, param: Void?) {
        visitUnary(node)
    }
    
    func visit(node: QLNot, param: Void?) {
        visitUnary(node)
    }
    
    func visitBinary(binary: QLBinary) {
        binary.lhs.accept(self, param: nil)
        binary.rhs.accept(self, param: nil)
    }
    
    func visit(node: QLAdd, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLSub, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLMul, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLDiv, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLPow, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLGe, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLGt, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLLe, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLLt, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLEq, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLNe, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLAnd, param: Void?) {
        visitBinary(node)
    }
    
    func visit(node: QLOr, param: Void?) {
        visitBinary(node)
    }
}


// MARK: - Private methods

extension ScopeChecker {
    
    private func resetInternals(symbolTable: SymbolTable) {
        scopedSymbolTable = ScopedSymbolTable(parent: nil)
        self.symbolTable = symbolTable
        errors = []
        warnings = []
    }
    
    private func checkScopes(form: QLForm) {
        self.visit(form.block, param: nil)
    }
    
    private func assignScope(question: QLQuestion) {
        if let symbol = symbolTable.retrieve(question.identifier.id) {
            do {
                try scopedSymbolTable.assign(question.identifier.id, symbol: symbol)
            } catch let warning as OverridingVariable {
                collectWarning(warning)
            } catch let error as MultipleDeclarations {
                collectError(error)
            } catch let error {
                collectError(error)
            }
        }
    }
    
    private func retrieveSymbolType(identifier: String) {
        if scopedSymbolTable.retrieve(identifier) == nil {
            collectError(UndefinedVariableError(description: "Variable \"\(identifier)\" is not defined at this scope"))
        }
    }
    
    private func collectError(error: SemanticError) {
        self.errors.append(error)
    }
    
    private func collectError(error: ErrorType) {
        self.errors.append(SystemError(error: error))
    }
    
    private func collectWarning(warning: SemanticWarning) {
        self.warnings.append(warning)
    }
}