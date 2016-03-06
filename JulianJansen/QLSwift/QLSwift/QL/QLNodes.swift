//
//  QLNodes.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol ASTNode {}

protocol QLLiteral: ASTNode {}
protocol QLStatement: ASTNode {}
protocol QLExpression: QLStatement {}
protocol QLOperator {}


class QLForm: ASTNode {
    let formName: String
    let codeBlock: [QLStatement]
    
    init(formName: String, codeBlock: [QLStatement]) {
        self.formName = formName
        self.codeBlock = codeBlock
    }
}

// MARK: Statements.

class QLQuestion: QLStatement {
    let name: String
    let variable: String
    let type: String
    
    init(name: String, variable: String, type: String) {
        self.name = name
        self.variable = variable
        self.type = type
    }
}

class QLIfStatement: QLStatement {
    let condition: QLExpression
    let codeBlock: [QLStatement]
    
    init(condition: QLExpression, codeBlock: [QLStatement]) {
        self.condition = condition
        self.codeBlock = codeBlock
    }
}

// MARK: Expressions.

class QLUnaryExpression: QLExpression {
    let expression: QLLiteral
    
    init(expression: QLLiteral) {
        self.expression = expression
    }
}

protocol QLBinaryExpression: QLExpression {
    var lhs: QLExpression { get }
    var rhs: QLExpression { get }
    
    init(lhs: QLExpression, rhs: QLExpression)
}

class QLVariable: QLExpression {
    var identifier: String
    
    init(identifier: String) {
        self.identifier = identifier
    }
}

class QLAndExpression: QLBinaryExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    required init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
}

// MARK: Literals.

class QLBool: QLLiteral {
    let boolean: Bool
    
    init(boolean: Bool) {
        self.boolean = boolean
    }
}

class QLString: QLLiteral {
    let string: String
    
    init(string: String) {
        self.string = string
    }
}

class QLInteger: QLLiteral {
    let integer: Int
    
    init(integer: Int) {
        self.integer = integer
    }
}

class QLDate: QLLiteral {
    let date: NSDate
    
    init(date: NSDate) {
        self.date = date
    }
}

class QLDecimal: QLLiteral {
    let decimal: Double
    
    init(decimal: Double) {
        self.decimal = decimal
    }
}

class QLMoney: QLLiteral {
    let money: Float
    
    init(money: Float) {
        self.money = money
    }
}