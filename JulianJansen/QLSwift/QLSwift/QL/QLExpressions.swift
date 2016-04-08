//
//  QLExpressions.swift
//  QLSwift
//
//  Created by Julian Jansen on 08-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol QLExpression: TypeVisitable, ExpressionVisitable {}

class QLVariable: QLExpression {
    let name: String
    
    init(name: String) {
        self.name = name
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLNotExpression: QLExpression {
    let expression: QLExpression
    
    init(expression: QLExpression) {
        self.expression = expression
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLGreaterThanExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLSmallerThanExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLGreaterOrIsExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLSmallerOrIsExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLIsNotExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLIsExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLMultiplyExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLDivideExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLAddExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLSubtractExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLAndExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLOrExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}