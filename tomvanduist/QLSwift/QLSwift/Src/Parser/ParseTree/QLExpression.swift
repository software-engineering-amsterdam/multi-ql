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


// Mark: Implode

extension QLExpressionVariable {
    func implode() -> Expression {
        return variable.implode()
    }
}

extension QLBoolean {
    func implode() -> Expression {
        return BooleanField()
    }
}

extension QLMoney {
    func implode() -> Expression {
        return MoneyField(expression: expr?.implode())
    }
}

extension QLExpressionLiteral {
    func implode() -> Expression {
        return literal.implode()
    }
}
