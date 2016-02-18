//
//  FormNodeVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

protocol FormNodeVisitor {
    typealias AbstractType
    
    func visit(node: Form) -> AbstractType
    func visit(node: Question)
    func visit(node: Conditional)
    func visit(node: Block)
    func visit(node: Identifier)
    func visit(node: BooleanField)
    func visit(node: StringField)
    func visit(node: MoneyField)
    func visit(node: StringLiteral)
    func visit(node: IntegerLiteral)
    func visit(node: FloatLiteral)
    func visit(node: BooleanLiteral)
    func visit(node: Prefix)
    func visit(node: Infix)
}

class FormNodeVisitorThunk<T> : FormNodeVisitor {
    // closure which will be used to implement `magic()` as declared in the protocol
    private let visit : (node: Form) -> T
    
    // `T` is effectively a handle for `AbstractType` in the protocol
    init<P : FormNodeVisitor where P.AbstractType == T>(_ dep : P) {
        visit = { (node: Form) in dep.visit(node)}
    }
    
    func visit(node: Form) -> T {
        // any protocol methods are implemented by forwarding
        return visit(node: node)
    }
    
    func visit(node: Question) {}
    func visit(node: Conditional) {}
    func visit(node: Block) {}
    func visit(node: Identifier) {}
    func visit(node: BooleanField) {}
    func visit(node: StringField) {}
    func visit(node: MoneyField) {}
    func visit(node: StringLiteral) {}
    func visit(node: IntegerLiteral) {}
    func visit(node: FloatLiteral) {}
    func visit(node: BooleanLiteral) {}
    func visit(node: Prefix) {}
    func visit(node: Infix) {}
}

class ConcreteFormNodeVisitor: FormNodeVisitorThunk<Void> {
    override func visit(node: Form) {
        node.identifier.accept(self)
        node.statement.accept(self)
    }
override     
    func visit(node: Question) {
        node.identifier.accept(self)
        node.expression.accept(self)
    }
    
    override func visit(node: Conditional) {
        node.condition.accept(self)
        node.ifBlock.accept(self)
        node.elseBlock?.accept(self)
    }
    
    override func visit(node: Block) {
        for statement in node.block {
            statement.accept(self)
        }
    }
override     
    func visit(node: Identifier) {
    }
override     
    func visit(node: BooleanField) {
    }
    
    override func visit(node: StringField) {
    }
    
    override func visit(node: MoneyField) {
        node.expression?.accept(self)
    }
    
    override func visit(node: StringLiteral) {
    }
    
    override func visit(node: IntegerLiteral) {
    }
    override func visit(node: FloatLiteral) {
    }
    override func visit(node: BooleanLiteral) {
    }
override     
    func visit(node: Prefix) {
        node.rhs.accept(self)
    }
    
    override func visit(node: Infix) {
        node.lhs.accept(self)
        node.rhs.accept(self)
    }
}

protocol FormNodeVisitable {
    func accept(visitor: ConcreteFormNodeVisitor)
}

extension Form {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Question {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Conditional {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Block {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Identifier {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension BooleanField {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension StringField {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension MoneyField {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension StringLiteral {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension IntegerLiteral {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension FloatLiteral {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension BooleanLiteral {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Prefix {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}

extension Infix {
    func accept(visitor: ConcreteFormNodeVisitor) {
        visitor.visit(self)
    }
}