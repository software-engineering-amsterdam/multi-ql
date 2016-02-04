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

class QLExpressionVariable: QLExpression {
    let variable: QLVariable
    
    init(variable: QLVariable) {
        self.variable = variable
    }
    
    override var description: String { return super.description + ".variable = \(variable)" }
}

class QLBoolean: QLExpression {
}

class QLMoney: QLExpression {
    var expr: QLExpression?
    
    init(expr: QLExpression? = nil) {
        self.expr = expr
    }
    
    override var description: String { return super.description + "( \(expr) )" }
}