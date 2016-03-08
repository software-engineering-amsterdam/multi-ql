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
    let variable: QLExpression
    let type: String
    
    init(name: String, variable: QLExpression, type: String) {
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

class QLVariable: QLExpression {
    let identifier: String
    
    init(identifier: String) {
        self.identifier = identifier
    }
}

class QLUnaryExpression: QLExpression {
    let expression: QLLiteral
    
    init(expression: QLLiteral) {
        self.expression = expression
    }
}

class QLNotExpression: QLExpression {
    let expression: QLExpression
    
    init(expression: QLExpression) {
        self.expression = expression
    }
}

class QLBinaryExpression: QLExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
}

class QLGreaterThanExpression: QLBinaryExpression { }

class QLSmallerThanExpression: QLBinaryExpression { }

class QLGreaterOrIsExpression: QLBinaryExpression { }

class QLSmallerOrISExpression: QLBinaryExpression { }

class QLIsNotExpression: QLBinaryExpression { }

class QLIsExpression: QLBinaryExpression { }

class QLMultiplyExpression: QLBinaryExpression { }

class QLDivideExpression: QLBinaryExpression { }

class QLAddExpression: QLBinaryExpression { }

class QLSubtractExpression: QLBinaryExpression { }

class QLAndExpression: QLBinaryExpression { }

class QLOrExpression: QLBinaryExpression { }





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
    let day: Int
    let month: Int
    let year: Int
    
    init(day: Int, month: Int, year: Int) {
        self.day = day
        self.month = month
        self.year = year
    }
}

class QLDecimal: QLLiteral {
    let decimal: Int
    
    init(decimal: Int) {
        self.decimal = decimal
    }
}

class QLMoney: QLLiteral {
    let money: Float
    
    init(money: Float) {
        self.money = money
    }
}