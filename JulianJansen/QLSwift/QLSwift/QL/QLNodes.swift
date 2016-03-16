//
//  QLNodes.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol ASTNode: Visitable {}
protocol QLLiteral: ASTNode {}
protocol QLStatement: ASTNode {}
protocol QLExpression: QLStatement {
    var identifier:Int { get }
}
protocol QLOperator {}

class QLForm: ASTNode, Visitable {
    let formName: String
    let codeBlock: [QLStatement]
    
    init(formName: String, codeBlock: [QLStatement]) {
        self.formName = formName
        self.codeBlock = codeBlock
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

// MARK: Statements.

class QLQuestion: QLStatement, Visitable {
    let name: String
    let variable: QLVariable
    let type: QLLiteral
    
    init(name: String, variable: QLVariable, type: QLLiteral) {
        self.name = name
        self.variable = variable
        self.type = type
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLIfStatement: QLStatement, Visitable {
    let condition: QLExpression
    let codeBlock: [QLStatement]
    
    init(condition: QLExpression, codeBlock: [QLStatement]) {
        self.condition = condition
        self.codeBlock = codeBlock
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

// MARK: Expressions.

extension QLExpression {
    func getID() -> Int {
        return self.identifier
    }
}

class QLVariable: QLExpression {
    let identifier: Int
    let name: String
    let type: QLLiteral
    
    init(name: String, identifier: Int) {
        self.identifier = identifier
        self.name = name
        self.type = QLUnknownLiteral()
    }
    
    init(name: String, type: QLLiteral, identifier: Int) {
        self.identifier = identifier
        self.name = name
        self.type = type
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLUnaryExpression: QLExpression {
    let identifier: Int
    let literal: QLLiteral
    
    init(literal: QLLiteral, identifier: Int) {
        self.identifier = identifier
        self.literal = literal
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLNotExpression: QLExpression {
    let identifier: Int
    let expression: QLExpression
    
    init(expression: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.expression = expression
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

protocol QLBinaryExpression: QLExpression {
    var lhs: QLExpression { get }
    var rhs: QLExpression { get }
}

class QLGreaterThanExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLSmallerThanExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLGreaterOrIsExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLSmallerOrISExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLIsNotExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLIsExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLMultiplyExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLDivideExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLAddExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLSubtractExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLAndExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLOrExpression: QLBinaryExpression {
    let identifier: Int
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression, identifier: Int) {
        self.identifier = identifier
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

// MARK: Literals.

class QLUnknownLiteral: QLLiteral {    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLBool: QLLiteral {
    var boolean = Bool()
    
    init() { }
    
    init(boolean: Bool) {
        self.boolean = boolean
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLString: QLLiteral {
    var string = String()
    
    init() { }
    
    init(string: String) {
        self.string = string
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLInteger: QLLiteral {
    var integer = Int()
    
    init() { }
    
    init(integer: Int) {
        self.integer = integer
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLDate: QLLiteral {
    var day = Int()
    var month = Int()
    var year = Int()
    
    init() { }
    
    init(day: Int, month: Int, year: Int) {
        self.day = day
        self.month = month
        self.year = year
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLDecimal: QLLiteral {
    var decimal = Int()
    
    init() { }
    
    init(decimal: Int) {
        self.decimal = decimal
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLMoney: QLLiteral {
    var money = Float()
    
    init() { }
    
    init(money: Float) {
        self.money = money
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}