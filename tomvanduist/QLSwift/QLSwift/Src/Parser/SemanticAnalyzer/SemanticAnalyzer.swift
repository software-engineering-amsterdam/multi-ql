//
//  SemanticAnalyzer.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

class SemanticAnalyser: FormNodeVisitor {
    
    let context: Context
    var error: SemanticError = SemanticError.None
    
    init(context: Context) {
        self.context = context
    }
    
    func analyze(node: FormNode) throws -> FormNode {
        error = SemanticError.None
        
        node.accept(self)
        
        if case SemanticError.None = error {
            return node
        } else {
            throw error
        }
    }
    
    func visit(node: Form) {
        node.identifier.accept(self)
        node.statement.accept(self)
    }
    
    func visit(node: Question) {
        node.identifier.accept(self)
        node.expression.accept(self)
        
        do { try context.assign(node.identifier, object: (type(node.expression), node.expression)) }
        catch let e { error.collect(e) }
    }
    
    func visit(node: Conditional) {
        node.condition.accept(self)
        node.ifBlock.accept(self)
        node.elseBlock?.accept(self)
        
        if (type(node.condition) != Type.Bool) {
            error.collect(SemanticError.TypeMismatch(description: "If statement condition must be of type Bool: \(node.condition)"))
        }
    }
    
    func visit(node: Block) {
        for statement in node.block {
            statement.accept(self)
        }
    }
    
    func visit(node: Identifier) {
        if let o: Object = context.retrieve(node) {
            node.expression = o.expression
        }
    }
    
    func visit(node: MoneyField) {
        node.expression?.accept(self)
        
        if let expression = node.expression {
            if type(expression) != Type.Number {
                error.collect(SemanticError.TypeMismatch(description: "Money expression must result in a numerical value: \(node.expression)"))
            }
        }
    }
    
    func visit(node: Prefix) {
        node.rhs.accept(self)
        
        if (type(node) != type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Prefix type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    func visit(node: Infix) {
        node.lhs.accept(self)
        node.rhs.accept(self)
        
        let typeError = { [unowned self] in
            self.error.collect(SemanticError.TypeMismatch(description: "Infix type does not match expression type(s). \(node.type) does not match \(node.lhs.type) and \(node.rhs.type)."))
        }
        
        switch (node.op) {
            case .Gt :
                fallthrough
            case .Ge:
                fallthrough
            case .Lt:
                fallthrough
            case .Le:
                fallthrough
            case .Add:
                fallthrough
            case .Sub:
                fallthrough
            case .Mul:
                fallthrough
            case .Div:
                fallthrough
            case .Pow:
                if (type(node.lhs) != Type.Number || type(node.rhs) != Type.Number) {
                    typeError()
                }
            case .Or:
                fallthrough
            case .And:
                if (type(node.lhs) != Type.Bool || type(node.rhs) != Type.Bool) {
                    typeError()
                }
            case .Eq:
                fallthrough
            case .Ne:
                if (type(node.lhs) != type(node.rhs)) {
                    typeError()
                }
        }
    }
    
    private func type(node: Expression) -> Type {
        let type = node.resolveType(context)
        
        if case .Unknown = type {
            error.collect(SemanticError.NotDefined(description: "\(node) is not (yet) defined."))
        }
        
        return type
    }
}
