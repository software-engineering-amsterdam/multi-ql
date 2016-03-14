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
    let literal: QLLiteral
    
    init(literal: QLLiteral) {
        self.literal = literal
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

//protocol QLBinaryExpression: QLExpression {
//    var lhs: QLExpression { set get }
//    var rhs: QLExpression { set get }
//}


//class QLTest: QLExpression {
//    var lhs: Int
//    var rhs: Int
//    
//    init(lhs: Int, rhs: Int) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//    }
//}
//
//class QLSubTest: QLTest {
//    override init(lhs: Int, rhs: Int) {
//        super.init(lhs: lhs, rhs: rhs)
//    }
//}

class QLBinaryExpression: QLExpression {
    let lhs: QLExpression
    let rhs: QLExpression
    
    init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func accept(visitor: Visitor) {
        visitor.visit(self)
    }

}

class QLGreaterThanExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLSmallerThanExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLGreaterOrIsExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLSmallerOrISExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLIsNotExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLIsExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLMultiplyExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLDivideExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLAddExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLSubtractExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLAndExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}

class QLOrExpression: QLBinaryExpression {
    override init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
}




//
//class QLGreaterThanExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLSmallerThanExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLGreaterOrIsExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLSmallerOrISExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLIsNotExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLIsExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLMultiplyExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLDivideExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLAddExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLSubtractExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLAndExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}
//
//class QLOrExpression: QLBinaryExpression {
//    var lhs: QLExpression
//    var rhs: QLExpression
//    
//    init(lhs: QLExpression, rhs: QLExpression) {
//        self.lhs = lhs
//        self.rhs = rhs
//    }
//    
//    func accept(visitor: Visitor) {
//        visitor.visit(self)
//    }
//}


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