//
//  QLContext.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

protocol QLContextDelegate: class {
    func contextUpdated(context: QLContext)
}


class QLContext {
    private var variableContext = [String: NSObject]()
    private var computedContext = [String: QLExpression]()
    
    private var delegates = [QLContextDelegate]()
    
    
    required init(form: QLForm) {
        setDefaults(form)
    }
    
    func subscribe(delegate: QLContextDelegate) {
        delegates.append(delegate)
    }
    
    func unsubscribe(delegate: QLContextDelegate) {
        delegates = delegates.filter { "\($0)" != "\(delegate)" }
    }
    
    func assign(identifier: String, value: NSObject?) {
        variableContext[identifier] = value
        
        notifyDelegates()
    }
    
    func assign(identifier: String, expression: QLExpression) {
        computedContext[identifier] = expression
        
        notifyDelegates()
    }
    
    func retrieve(identifier: String) -> NSObject? {
        if let value = variableContext[identifier] {
            return value
        }
        
        guard let expression = computedContext[identifier]
            else { return nil }
        
        return expression.eval(self)
    }
    
    private func setDefaults(form: QLForm) {
        form.block.accept(QLContextVisitor(), param: self)
    }
    
    private func notifyDelegates() {
        for delegate in delegates {
            delegate.contextUpdated(self)
        }
    }
}


private class QLContextVisitor: QLStatementVisitor {
    
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
        param.assign(node.identifier.id, value: nil)
    }
    
    func visit(node: QLComputedQuestion, param: QLContext) -> Void {
        param.assign(node.identifier.id, expression: node.expression)
    }
}