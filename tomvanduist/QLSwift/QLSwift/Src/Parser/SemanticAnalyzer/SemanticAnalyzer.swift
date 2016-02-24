//
//  SemanticAnalyzer.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

class SemanticAnalyser: ASTNodeVisitor {
    
    var context: Context
    var error: SemanticError = SemanticError.None
    
    init(context: Context) {
        self.context = context
    }
    
    func analyze(node: ASTNode) throws -> ASTNode {
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
            error.collect(SemanticError.TypeMismatch(description: "Unary type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    func visit(node: Not) {
        node.rhs.accept(self)
        
        if (type(node) !== type(node.rhs)) {
            error.collect(SemanticError.TypeMismatch(description: "Unary type does not match expression type. \(node.type) does not match \(node.rhs.type)."))
        }
    }
    
    func collectBinaryTypeError(node: Binary) {
        self.error.collect(SemanticError.TypeMismatch(description: "Binary type does not match expression type(s). \(node.type) does not match \(node.lhs.type) and \(node.rhs.type)."))
    }
    
    func visitBinary(node: Binary) {
        node.lhs.accept(self)
        node.rhs.accept(self)
    }

    func visitBinaryNumber(node: Binary) {
        visitBinary(node)
        
        if (type(node.lhs) !== NumberType() || type(node.rhs) !== NumberType()) {
            collectBinaryTypeError(node)
        }
    }
    
    func visit(node: Add) {
        visitBinaryNumber(node)
    }
    
    func visit(node: Sub) {
        visitBinaryNumber(node)
    }
    
    func visit(node: Mul) {
        visitBinaryNumber(node)
    }
    
    func visit(node: Div) {
        visitBinaryNumber(node)
    }
    
    func visit(node: Pow) {
        visitBinaryNumber(node)
    }
    
    func visit(node: Ge) {
        visitBinaryNumber(node)
    }
    
    func visit(node: Gt) {
        visitBinaryNumber(node)
    }
    
    func visit(node: Le) {
        visitBinaryNumber(node)
    }
    
    func visit(node: Lt) {
        visitBinaryNumber(node)
    }
    
    func visitBinaryEq(node: Binary) {
        visitBinary(node)
        
        if (type(node.lhs) !== type(node.rhs)) {
            collectBinaryTypeError(node)
        }
    }
    
    func visit(node: Eq) {
        visitBinaryEq(node)
    }
    
    func visit(node: Ne) {
        visitBinaryEq(node)
    }
    
    func visitBinaryBool(node: Binary) {
        visitBinary(node)
        
        if (type(node.lhs) !== BooleanType() || type(node.rhs) !== BooleanType()) {
            collectBinaryTypeError(node)
        }
    }
    
    func visit(node: And) {
        visitBinaryBool(node)
    }
    
    func visit(node: Or) {
        visitBinaryBool(node)
    }
    
    private func type(node: Expression) -> ExpressionType {
        let type = node.type
        
        if node.type === UnknownType() {
            error.collect(SemanticError.NotDefined(description: "\(node) is not (yet) defined."))
        }
    
        return type
    }
}
