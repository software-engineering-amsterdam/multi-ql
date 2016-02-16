//
//  FormNodeVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

protocol FormNodeVisitor {
    func visit(node: Form)
    func visit(node: Question)
    func visit(node: Conditional)
    func visit(node: Block)
    func visit(node: Identifier)
    func visit(node: BooleanField)
    func visit(node: MoneyField)
    func visit(node: StringLiteral)
    func visit(node: IntegerLiteral)
    func visit(node: FloatLiteral)
    func visit(node: BooleanLiteral)
    func visit(node: Prefix)
    func visit(node: Infix)
}

extension FormNodeVisitor {
    func visit(node: Form) {
        node.identifier.accept(self)
        node.statement.accept(self)
    }
    
    func visit(node: Question) {
        node.identifier.accept(self)
        node.expression.accept(self)
    }
    
    func visit(node: Conditional) {
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
        node.rhs.accept(self)
    }
    
    func visit(node: Infix) {
        node.lhs.accept(self)
        node.rhs.accept(self)
    }
}

protocol FormNodeVisitable {
    func accept(visitor: FormNodeVisitor) -> Void
}

extension Form {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Question {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Conditional {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Block {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Identifier {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension BooleanField {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension MoneyField {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension StringLiteral {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension IntegerLiteral {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension FloatLiteral {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension BooleanLiteral {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Prefix {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Infix {
    func accept(visitor: FormNodeVisitor) {
        visitor.visit(self)
    }
}