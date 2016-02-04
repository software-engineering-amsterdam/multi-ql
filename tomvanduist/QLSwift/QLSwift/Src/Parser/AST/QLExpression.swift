//
//  QLExpression.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

class QLExpression: NSObject {
    
}

class QLString: QLExpression {
    let value: String
    
    init(value: String) {
        self.value = value
    }
    
    override var description:String { return super.description + ".value \(value)" }
}

class QLBoolean: QLExpression {
}

class QLBooleanValue: QLExpression {
    let value: Bool
    
    init(value: Bool) {
        self.value = value;
    }
    
    override var description:String { return super.description + ".value \(value ? "true" : "false")" }
}

class QLMoney: QLExpression {
    var expr: QLExpression?
    
    init(expr: QLExpression? = nil) {
        self.expr = expr
    }
    
    override var description:String { return super.description + "( \(expr) )" }
}

class QLSub: QLExpression {
    let lhs, rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    override var description:String { return super.description + "( \(lhs) - \(rhs) )" }
}