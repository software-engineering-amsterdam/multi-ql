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
    private var computedContext = [String: (type: QLType, expression: QLExpression)]()
    
    required init(form: QLForm, symbolTable: SymbolTable) {
        setDefaults(form, symbolTable: symbolTable)
    }
    
    func assign(identifier: String, value: NSObject) {
        variableContext[identifier] = value
    }
    
    func assign(identifier: String, expression: QLExpression) {
        guard let (type, _) = computedContext[identifier]
            else { fatalError("This is not the context that you are looking for") }
        
        computedContext[identifier] = (type, expression)
    }
    
    func assign(identifier: String, object: (QLType, QLExpression)) {
        computedContext[identifier] = object
    }
    
    func retrieve(identifier: String) -> NSObject {
        if let value = variableContext[identifier] {
            return value
        }
        
        guard let (_, expression) = computedContext[identifier]
            else { fatalError("This is not the context that you are looking for") }
        
        return expression.eval(self)
    }
    
    func retrieveType(computedQuestion: QLComputedQuestion) -> QLType {
        guard let (type, _) = computedContext[computedQuestion.identifier.id]
            else { fatalError("This is not the context that you are looking for") }
        
        return type
    }
    
    private func setDefaults(form: QLForm, symbolTable: SymbolTable) {
        form.block.accept(QLContextVisitor(context: self, symbolTable: symbolTable), param: nil)
    }
}


private class QLContextVisitor: QLStatementVisitor {
    
    typealias QLStatementVisitorParam   = Void?
    typealias QLStatementVisitorReturn  = Void
    
    let context: QLContext
    let symbolTable: SymbolTable
    
    init(context: QLContext, symbolTable: SymbolTable) {
        self.context = context
        self.symbolTable = symbolTable
    }
    
    
    // MARK: - QLStatementVisitor conformance
    
    func visit(node: QLBlock, param: Void?) -> Void {
        for statement in node.block {
            statement.accept(self, param: param)
        }
    }
    
    func visit(node: QLConditional, param: Void?) -> Void {
        for statement in node.ifBlock.block {
            statement.accept(self, param: param)
        }
    }
    
    func visit(node: QLVariableQuestion, param: Void?) -> Void {
        context.assign(node.identifier.id, value: node.type.defaultValue)
    }
    
    func visit(node: QLComputedQuestion, param: Void?) -> Void {
        guard let type = symbolTable.retrieveType(node.identifier.id)
            else { fatalError("This is not the context that you are looking for") }
        
        context.assign(node.identifier.id, object: (type, node.expression))
    }
}