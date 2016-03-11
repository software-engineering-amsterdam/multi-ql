//
//  Context.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

protocol ContextDelegate: class {
    func contextUpdated(context: Context)
}


class Context {
    private var variableContext = [String: NSObject]()
    private var computedContext = [String: QLExpression]()
    
    private var delegates = [ContextDelegate]()
    
    
    required init(form: QLForm) {
        setDefaults(form)
    }
    
    func subscribe(delegate: ContextDelegate) {
        delegates.append(delegate)
    }
    
    func unsubscribe(delegate: ContextDelegate) {
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
        form.block.accept(ContextVisitor(), param: self)
    }
    
    private func notifyDelegates() {
        for delegate in delegates {
            delegate.contextUpdated(self)
        }
    }
}


private class ContextVisitor: QLStatementVisitor {
    
    // MARK: - QLStatementVisitor conformance
    
    func visit(node: QLBlock, param: Context) -> Void {
        for statement in node.block {
            statement.accept(self, param: param)
        }
    }
    
    func visit(node: QLConditional, param: Context) -> Void {
        for statement in node.ifBlock.block {
            statement.accept(self, param: param)
        }
    }
    
    func visit(node: QLVariableQuestion, param: Context) -> Void {
        param.assign(node.identifier.id, value: nil)
    }
    
    func visit(node: QLComputedQuestion, param: Context) -> Void {
        param.assign(node.identifier.id, expression: node.expression)
    }
}