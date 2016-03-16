//
//  ScopeChecker.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation


private class ScopedMap: Map<QLType> {
    let parent: ScopedMap?
    
    init(parent: ScopedMap? = nil) {
        self.parent = parent
    }
    
    override func retrieve(identifier: String) -> QLType? {
        if let o = super.retrieve(identifier) {
            return o
        }
        
        return parent?.retrieve(identifier)
    }
}


internal class ScopeChecker: SemanticAnalysisRule, QLStatementVisitor, QLExpressionVisitor {
    private var scopedMap: ScopedMap = ScopedMap(parent: nil)
    private var analysisResult: SemanticAnalysisResult = SemanticAnalysisResult()
    
    
    func run(form: QLForm, context: Context) -> SemanticAnalysisResult {
        resetInternals()
        
        checkScopes(form, context: context)
        
        return analysisResult
    }
}


// MARK: - QLStatementVisitor conformance

extension ScopeChecker {
    
    func visit(node: QLVariableQuestion, param context: Context) {
        // no-op
    }
    
    func visit(node: QLComputedQuestion, param context: Context) {
        node.expression.accept(self, param: context)
    }
    
    func visit(node: QLConditional, param context: Context) {
        node.condition.accept(self, param: context)
        node.ifBlock.accept(self, param: context)
    }
    
    func visit(node: QLBlock, param context: Context) {
        scopedMap = ScopedMap(parent: scopedMap)
        
        // Assign scopes
        for question in node.questions() {
            assignScope(question, context: context)
        }
        
        // Visit questions
        for question in node.questions() {
            question.accept(self, param: context)
        }
        
        // Visit deeper scopes
        for conditional in node.conditionals() {
            conditional.accept(self, param: context)
        }
        
        if let parentScope = scopedMap.parent {
            scopedMap = parentScope
        }
    }
}


// MARK: - QLExpressionVisitor conformance

extension ScopeChecker {
    
    func visit(node: QLVariable, param context: Context) {
        return retrieveType(node.id)
    }
    
    func visit(node: QLLiteralExpression, param context: Context) {
    }
    
    private func processUnary(unary: QLUnary, context: Context) {
        unary.rhs.accept(self, param: context)
    }
    
    func visit(node: QLNeg, param context: Context) {
        processUnary(node, context: context)
    }
    
    func visit(node: QLNot, param context: Context) {
        processUnary(node, context: context)
    }
    
    private func processBinary(binary: QLBinary, context: Context) {
        binary.lhs.accept(self, param: context)
        binary.rhs.accept(self, param: context)
    }
    
    func visit(node: QLAdd, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLSub, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLMul, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLDiv, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLPow, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLGe, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLGt, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLLe, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLLt, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLEq, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLNe, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLAnd, param context: Context) {
        processBinary(node, context: context)
    }
    
    func visit(node: QLOr, param context: Context) {
        processBinary(node, context: context)
    }
}


// MARK: - Private methods

extension ScopeChecker {
    
    private func resetInternals() {
        scopedMap = ScopedMap(parent: nil)
        analysisResult = SemanticAnalysisResult()
    }
    
    private func checkScopes(form: QLForm, context: Context) {
        self.visit(form.block, param: context)
    }
    
    private func assignScope(question: QLQuestion, context: Context) {
        if let newType = context.retrieveType(question.identifier.id) {
            
            // assign
            defer {
                scopedMap.assign(question.identifier.id, value: newType)
            }
            
            // Collect any errors or warnings
            if let currentType = scopedMap.retrieve(question.identifier.id) {
                if currentType === newType  {
                    analysisResult.collectWarning(
                        OverridingVariable(description: "The variable \'\(question.identifier.id)\' overrides an earlier instance.")
                    )
                } else if currentType !== QLUnknownType.self {
                    analysisResult.collectError(
                        MultipleDeclarations(description: "The variable \'\(question.identifier.id)\' is multiply declared as both \'\(currentType.toString())\' and \'\(newType.toString())\'")
                    )
                }
            }
        }
    }
    
    private func retrieveType(identifier: String) {
        if scopedMap.retrieve(identifier) == nil {
            analysisResult.collectError(UndefinedVariableError(description: "Variable \"\(identifier)\" is not defined at this scope"))
        }
    }
}