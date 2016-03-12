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
protocol QLExpression: QLStatement {}
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

class QLVariable: QLExpression {
    let identifier: String
    
    init(identifier: String) {
        self.identifier = identifier
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLUnaryExpression: QLExpression {
    let expression: QLLiteral
    
    init(expression: QLLiteral) {
        self.expression = expression
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLNotExpression: QLExpression {
    let expression: QLExpression
    
    init(expression: QLExpression) {
        self.expression = expression
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

protocol QLBinaryExpression: QLExpression {
    var lhs: QLExpression { set get }
    var rhs: QLExpression { set get }
}

class QLGreaterThanExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLSmallerThanExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLGreaterOrIsExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLSmallerOrISExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLIsNotExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLIsExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLMultiplyExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLDivideExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLAddExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLSubtractExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLAndExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLOrExpression: QLBinaryExpression {
    var lhs: QLExpression
    var rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}


// MARK: Literals.

class QLBool: QLLiteral {
    var boolean = Bool()
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLString: QLLiteral {
    var string = String()
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLInteger: QLLiteral {
    var integer = Int()
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLDate: QLLiteral {
    var day = Int()
    var month = Int()
    var year = Int()
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLDecimal: QLLiteral {
    var decimal = Int()
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}

class QLMoney: QLLiteral {
    var money = Float()
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }
}