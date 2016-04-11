//
//  QLLiterals.swift
//  QLSwift
//
//  Created by Julian Jansen on 08-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol QLLiteral: QLExpression {
    var type: QLTypes { get set }
}

class QLBoolLiteral: QLLiteral {
    var value = Bool()
    var type = QLTypes.Boolean
    
    init() { }
    
    init(boolean: Bool) {
        self.value = boolean
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLStringLiteral: QLLiteral {
    var value = String()
    var type = QLTypes.String
    
    init() { }
    
    init(string: String) {
        self.value = string
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}

class QLIntegerLiteral: QLLiteral {
    var value = Int()
    var type = QLTypes.Integer
    
    init() { }
    
    init(integer: Int) {
        self.value = integer
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: ExpressionVisitor) -> AnyObject {
        return visitor.visit(self)
    }
}