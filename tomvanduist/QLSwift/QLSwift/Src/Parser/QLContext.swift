//
//  QLContext.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

class QLContext {
    private var variableContext = [String: NSObject]()
    private var computedContext = [String: QLExpression]()
    
    required init(form: QLForm) {
        setDefaults(form)
    }
    
    func assign(identifier: String, value: NSObject) {
        variableContext[identifier] = value
    }
    
    func assign(identifier: String, expression: QLExpression) {
        computedContext[identifier] = expression
    }
    
    func retrieve(identifier: String) -> NSObject {
        if let value = variableContext[identifier] {
            return value
        }
        
        guard let expression = computedContext[identifier]
            else {
                fatalError()
            }
        
        return expression.eval(self)
    }
    
    private func setDefaults(form: QLForm) {
        form.block.accept(QLContextVisitor(), param: self)
    }
}


private class QLContextVisitor: QLStatementVisitor {
    
    typealias QLStatementVisitorParam   = QLContext
    typealias QLExpressionVisitorParam  = QLContext
    
    
    // MARK: - QLStatementVisitor conformance
    
    func visit(node: QLBlock, param: QLContext) -> Void {
        for statement in node.block {
            statement.accept(self, param: param)
        }
    }
    
    func visit(node: QLConditional, param: QLContext) -> Void {
        for statement in node.ifBlock.block {
            statement.accept(self, param: param)
        }
    }
    
    func visit(node: QLVariableQuestion, param: QLContext) -> Void {
        param.assign(node.identifier.id, value: node.type.defaultValue)
    }
    
    func visit(node: QLComputedQuestion, param: QLContext) -> Void {
        param.assign(node.identifier.id, expression: node.expression)
    }
}