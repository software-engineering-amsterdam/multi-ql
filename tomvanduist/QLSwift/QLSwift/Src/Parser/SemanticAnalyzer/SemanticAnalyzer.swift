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
        do { try context.assign(node.identifier, object: (Type.Form, node.statement)) }
        catch let e { error.collect(e) }
        
        node.identifier.accept(self)
        node.statement.accept(self)
    }
    
    func visit(node: Question) {
        do { try context.assign(node.identifier, object: (type(node.expression), node.expression)) }
        catch let e { error.collect(e) }
        
        node.identifier.accept(self)
        node.expression.accept(self)
    }
    
    func visit(node: Conditional) {
        if (type(node.condition) != Type.Bool) {
            error.collect(SemanticError.TypeMismatch(description: "If statement condition must be of type Bool: \(node.condition)"))
        }
        
        node.condition.accept(self)
        node.ifBlock.accept(self)
        node.elseBlock?.accept(self)
    }
    
    func visit(node: Block) {
        for statement in node.block {
            statement.accept(self)
        }
    }
    
    func visit(node: Identifier) {
    }
    
    func visit(node: BooleanField) {
    }
    
    func visit(node: MoneyField) {
        if let expression = node.expression {
            if type(expression) != Type.Number {
                error.collect(SemanticError.TypeMismatch(description: "Money expression must result in a numerical value: \(node.expression)"))
            }
        }
        
        node.expression?.accept(self)
    }
    
    func visit(node: StringLiteral) {
    }
    
    func visit(node: IntegerLiteral) {
    }
    
    func visit(node: FloatLiteral) {
    }
    
    func visit(node: BooleanLiteral) {
    }
    
    func visit(node: Prefix) {
        if (type(node) != type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Prefix type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
        
        node.rhs.accept(self)
    }
    
    func visit(node: Infix) {
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
        
        node.lhs.accept(self)
        node.rhs.accept(self)
    }
    
    private func type(node: Expression) -> Type {
        let type = node.resolveType(context)
        
        if case .Unknown = type {
            error.collect(SemanticError.TypeMismatch(description: "Unable to resolve type of expression \(node)."))
        }
        
        return type
    }
}
