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


internal class ScopeChecker: SemanticAnalysisRule, TopDownStatement, TopDownExpression {
    private var scopedMap: ScopedMap = ScopedMap(parent: nil)
    private var analysisResult: SemanticAnalysisResult = SemanticAnalysisResult()
    
    
    func run(form: QLForm, context: Context) -> SemanticAnalysisResult {
        resetInternals()
        
        checkScopes(form, context: context)
        
        return analysisResult
    }
}

    
// MARK: - QLStatementVisitor

extension ScopeChecker {
    
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
    
    func defaultLeafResult(statement: QLStatement?, param context: Context) {
        return
    }
}


// MARK: - QLExpressionVisitor

extension ScopeChecker {
    
    func visit(node: QLVariable, param context: Context) {
        if checkScope(node.id) == false {
            analysisResult.collectError(UndefinedVariableError(description: "Variable \"\(node.id)\" is not defined at this scope"))
        }
    }
    
    func defaultLeafResult(node: QLExpression, param: Context) {
        return
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
        let newType = TypeInferer.sharedInstance.inferType(question, context: context)
            
        // assign
        defer {
            scopedMap.assign(question.identifier.id, value: newType)
        }
        
        // Collect any errors or warnings
        if let currentType = scopedMap.retrieve(question.identifier.id) {
            if currentType === newType  {
                analysisResult.collectWarning(
                    OverridingVariable(description: "The variable \'\(question.identifier.id)\' overrides a different instance.")
                )
            } else if currentType !== QLUnknownType.self {
                analysisResult.collectError(
                    MultipleDeclarations(description: "The variable \'\(question.identifier.id)\' is multiply declared as both \'\(currentType.toString())\' and \'\(newType.toString())\'")
                )
            }
        }
    }
    
    private func checkScope(identifier: String) -> Bool {
        return scopedMap.retrieve(identifier) != nil
    }
}