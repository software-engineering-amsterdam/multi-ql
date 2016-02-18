//
//  ASTNodeVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

protocol ASTNodeVisitor {
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
    func visit(node: Add)
    func visit(node: Sub)
    func visit(node: Mul)
    func visit(node: Div)
    func visit(node: Pow)
    func visit(node: Eq)
    func visit(node: Ne)
    func visit(node: Ge)
    func visit(node: Gt)
    func visit(node: Le)
    func visit(node: Lt)
    func visit(node: And)
    func visit(node: Or)
}

extension ASTNodeVisitor {
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
    
    func visit(node: StringField) {
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
    
    func visit(node: Neg) {
        node.rhs.accept(self)
    }
    
    func visit(node: Not) {
        node.rhs.accept(self)
    }
    
    func visitInfix(node: Infix) {
        node.lhs.accept(self)
        node.rhs.accept(self)
    }
    
    func visit(node: Add) {
    }
    
    func visit(node: Sub) {
    }
    
    func visit(node: Mul) {
    }
    
    func visit(node: Div) {
    }
    
    func visit(node: Pow) {
    }
    
    func visit(node: Eq) {
    }
    
    func visit(node: Ne) {
    }
    
    func visit(node: Ge) {
    }
    
    func visit(node: Gt) {
    }
    
    func visit(node: Le) {
    }
    
    func visit(node: Lt) {
    }
    
    func visit(node: And) {
    }
    
    func visit(node: Or) {
    }
}

protocol ASTNodeVisitable {
    func accept(visitor: ASTNodeVisitor)
}

extension Form {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Question {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Conditional {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Block {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Identifier {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension BooleanField {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension StringField {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension MoneyField {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension StringLiteral {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension IntegerLiteral {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension FloatLiteral {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension BooleanLiteral {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Neg {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Not {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Add {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Sub {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Mul {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Div {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Pow {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Eq {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Ne {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Ge {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Gt {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Le {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Lt {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension And {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}

extension Or {
    func accept(visitor: ASTNodeVisitor) {
        visitor.visit(self)
    }
}
