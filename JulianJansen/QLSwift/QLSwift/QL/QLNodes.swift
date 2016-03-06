//
//  QLNodes.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol ASTNode {}
protocol ASTTerminal: ASTNode {}
protocol ASTNonTerminal: ASTNode {}

protocol QLStatement: ASTNonTerminal {}

protocol QLExpression: QLStatement {}

protocol QLUnaryExpression: QLExpression {
    var expression: ASTTerminal { get }
    var codeBlock: [QLStatement] { get }
    
    init(expression: ASTTerminal, codeBlock: [QLStatement])
}

protocol QLBinaryExpression: QLExpression {
    var lhs: QLExpression { get }
    var rhs: QLExpression { get }
    
    init(lhs: QLExpression, rhs: QLExpression)
}

protocol QLOperator {}

// MARK: Non-terminals.

class QLForm: ASTNonTerminal {
    let formName: String
    let codeBlock: [QLStatement]
    
    init(formName: String, codeBlock: [QLStatement]) {
        self.formName = formName
        self.codeBlock = codeBlock
    }
}

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

class QLLiteralExpression: QLExpression {
    let expression: ASTTerminal
    
    init(expression: ASTTerminal) {
        self.expression = expression
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

// MARK: Terminals.

class QLBool: ASTTerminal {
    let boolean: Bool
    
    init(boolean: Bool) {
        self.boolean = boolean
    }
}

class QLString: ASTTerminal {
    let string: String
    
    init(string: String) {
        self.string = string
    }
}

class QLInteger: ASTTerminal {
    let integer: Int
    
    init(integer: Int) {
        self.integer = integer
    }
}

class QLDate: ASTTerminal {
    let date: NSDate
    
    init(date: NSDate) {
        self.date = date
    }
}

class QLDecimal: ASTTerminal {
    let decimal: Double
    
    init(decimal: Double) {
        self.decimal = decimal
    }
}

class QLMoney: ASTTerminal {
    let money: Float
    
    init(money: Float) {
        self.money = money
    }
}