//
//  QLExpression.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

protocol QLExpression {
    func implode() -> Expression
}

class QLExpressionVariable: QLExpression {
    let variable: QLVariable
    
    init(variable: QLVariable) {
        self.variable = variable
    }
}

class QLBoolean: QLExpression {
}

class QLString: QLExpression {
}

class QLMoney: QLExpression {
    var expr: QLExpression?
    
    init(expr: QLExpression? = nil) {
        self.expr = expr
    }
}

class QLExpressionLiteral: QLExpression {
    var literal: QLLiteral
    
    init(literal: QLLiteral) {
        self.literal = literal
    }
}