//
//  SemanticAnalyzer.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

class SemanticAnalyser: FormNodeVisitor {
    
    var context: Context
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
        
        if (type(node.condition) !== BooleanType()) {
            error.collect(SemanticError.TypeMismatch(description: "If statement condition must be of type Bool: \(node.condition)"))
        }

    }
    
    func visit(node: Block) {
        context = Context(parent: context)
        
        for statement in node.block {
            statement.accept(self)
        }
        
        if let parent = context.parent {
            context = parent
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
            if type(expression) !== NumberType() {
                error.collect(SemanticError.TypeMismatch(description: "Money expression must result in a numerical value: \(node.expression)"))
            }

        }
    }
    
    func visit(node: Neg) {
        node.rhs.accept(self)
        
        if (type(node) !== type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Prefix type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    func visit(node: Not) {
        node.rhs.accept(self)
        
        if (type(node) !== type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Prefix type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    func collectInfixTypeError(node: Infix) {
        self.error.collect(SemanticError.TypeMismatch(description: "Infix type does not match expression type(s). \(node.type) does not match \(node.lhs.type) and \(node.rhs.type)."))
    }
    
    func visitInfix(node: Infix) {
        node.lhs.accept(self)
        node.rhs.accept(self)
    }

    func visitInfixNumber(node: Infix) {
        visitInfix(node)
        
        if (type(node.lhs) !== NumberType() || type(node.rhs) !== NumberType()) {
            collectInfixTypeError(node)
        }
    }
    
    func visit(node: Add) {
        visitInfixNumber(node)
    }
    
    func visit(node: Sub) {
        visitInfixNumber(node)
    }
    
    func visit(node: Mul) {
        visitInfixNumber(node)
    }
    
    func visit(node: Div) {
        visitInfixNumber(node)
    }
    
    func visit(node: Pow) {
        visitInfixNumber(node)
    }
    
    func visit(node: Ge) {
        visitInfixNumber(node)
    }
    
    func visit(node: Gt) {
        visitInfixNumber(node)
    }
    
    func visit(node: Le) {
        visitInfixNumber(node)
    }
    
    func visit(node: Lt) {
        visitInfixNumber(node)
    }
    
    func visitInfixEq(node: Infix) {
        visitInfix(node)
        
        if (type(node.lhs) !== type(node.rhs)) {
            collectInfixTypeError(node)
        }
    }
    
    func visit(node: Eq) {
        visitInfixEq(node)
    }
    
    func visit(node: Ne) {
        visitInfixEq(node)
    }
    
    func visitInfixBool(node: Infix) {
        visitInfix(node)
        
        if (type(node.lhs) !== BooleanType() || type(node.rhs) !== BooleanType()) {
            collectInfixTypeError(node)
        }
    }
    
    func visit(node: And) {
        visitInfixBool(node)
    }
    
    func visit(node: Or) {
        visitInfixBool(node)
    }
    
    private func type(node: Expression) -> FormNodeType {
        let type = node.type
        
        if node.type === UnknownType() {
            error.collect(SemanticError.NotDefined(description: "\(node) is not (yet) defined."))
        }
    
        return type
    }
}
