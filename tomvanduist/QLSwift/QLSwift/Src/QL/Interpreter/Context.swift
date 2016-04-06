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


class Map<T> {
    private var valueMap = [String: T]()
    
    init(valueMap: [String: T] = [:]) {
        self.valueMap = valueMap
    }
    
    func assign(identifier: String, value: T?) {
        if value != nil {
            valueMap[identifier] = value
        } else {
            valueMap.removeValueForKey(identifier)
        }
    }
    
    func retrieve(identifier: String) -> T? {
        return valueMap[identifier]
    }
    
    func getMap() -> [String: T] {
        return valueMap
    }
}


class Context {
    private var symbolTable = Map<QLType>()
    private var variableContext = Map<NSObject>()
    private var computedContext = Map<QLExpression>()
    
    private var delegates = [ContextDelegate]()
    
    
    required init(form: QLForm) throws {
        ComputedContextFiller().fill(form, context: self)
        symbolTable = try TypeInferer().inferTypes(form, context: self)
    }
    
    func subscribe(delegate: ContextDelegate) {
        delegates.append(delegate)
    }
    
    func unsubscribe(delegate: ContextDelegate) {
        delegates = delegates.filter { "\($0)" != "\(delegate)" }
    }
    
    func assign(identifier: String, type: QLType) {
        symbolTable.assign(identifier, value: type)
    }
    
    func assign(identifier: String, value: NSObject?) {
        variableContext.assign(identifier, value: value)
        
        notifyDelegates()
    }
    
    func assign(identifier: String, expression: QLExpression) {
        computedContext.assign(identifier, value: expression)
        
        notifyDelegates()
    }
    
    func retrieveType(identifier: String) -> QLType? {
        return symbolTable.retrieve(identifier)
    }
    
    func retrieveValue(identifier: String) -> NSObject? {
        return variableContext.retrieve(identifier)
    }
    
    func retrieveExpression(identifier: String) -> QLExpression? {
        return computedContext.retrieve(identifier)
    }
    
    private func notifyDelegates() {
        for delegate in delegates {
            delegate.contextUpdated(self)
        }
    }
}


private class ComputedContextFiller: TopDownStatement {
    
    func fill(form: QLForm, context: Context) {
        form.block.accept(self, param: context)
    }
    
    func visit(node: QLComputedQuestion, param context: Context) -> Void {
        context.assign(node.identifier.id, expression: node.expression)
    }
    
    func defaultLeafResult(statement: QLStatement?, param context: Context) -> Void {
        return
    }
}