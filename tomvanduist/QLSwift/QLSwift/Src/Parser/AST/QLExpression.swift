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
    
    override var description: String { return super.description + ".variable = \(variable); " }
}

class QLExpressionStatement: QLExpression {
    let statement: QLStatement
    
    init(statement: QLStatement) {
        self.statement = statement
    }
    
    override var description: String { return super.description + ".statement = \(statement); " }
}

class QLBoolean: QLExpression {
}

class QLIf: QLExpression {
    let statement: QLStatement
    let conditional: QLExpression
    
    init(conditional: QLExpression, statement: QLStatement) {
        self.statement = statement
        self.conditional = conditional
    }
    
    override var description: String { return super.description + ".conditional = \(conditional), .statement = \(statement); " }
}

class QLMoney: QLExpression {
    var expr: QLExpression?
    
    init(expr: QLExpression? = nil) {
        self.expr = expr
    }
    
    override var description: String { return super.description + "( \(expr) )" }
}