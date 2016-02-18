//
//  SemanticAnalyzer.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

class SemanticAnalyser: ConcreteFormNodeVisitor {
    
    var context: Context?
    var error: SemanticError? = SemanticError.None
    
    convenience init(context: Context) {    
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
    
    override func visit(node: Question) {
        do { try context.assign(node.identifier, object: (type(node.expression), node.expression)) }
        catch let e { error.collect(e) }
        
        super.visit(node)
    }
    
    override func visit(node: Conditional) {
        super.visit(node)
        
        if (type(node.condition) != Type.Bool) {
            error.collect(SemanticError.TypeMismatch(description: "If statement condition must be of type Bool: \(node.condition)"))
        }
    }
    
    override func visit(node: MoneyField) {
        super.visit(node)
        
        if let expression = node.expression {
            if type(expression) != Type.Number {
                error.collect(SemanticError.TypeMismatch(description: "Money expression must result in a numerical value: \(node.expression)"))
            }
        }
    }
    
    override func visit(node: Prefix) {
        super.visit(node)
        
        if (type(node) != type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Prefix type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    override func visit(node: Infix) {
        super.visit(node)
        
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
            error.collect(SemanticError.TypeMismatch(description: "Unable to resolve type of expression \(node)."))
        }
        
        return type
    }
}
