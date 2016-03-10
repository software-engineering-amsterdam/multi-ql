//
//  QLVisitors.swift
//  QLSwift
//
//  Created by Julian Jansen on 10-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

/// Abstract visitor.
protocol Visitor {
    func visit(qlform: QLForm)
    func visit(qlquestion: QLQuestion)
    func visit(qlifstatement: QLIfStatement)
    
    func visit(qlvariable: QLVariable)
    
    func visit(qlunaryexpression: QLUnaryExpression)
    func visit(qlnotexpression: QLNotExpression)
    func visit(qlbinaryexpression: QLBinaryExpression)
    
    func visit(qlbool: QLBool)
    func visit(qlstring: QLString)
    func visit(qlinteger: QLInteger)
    func visit(qldate: QLDate)
    func visit(qldecimal: QLDecimal)
    func visit(qlmoney: QLMoney)
}

/// Abstract visitable.
protocol Visitable {
    func accept(visitor: Visitor)
}

class TreePrinter: Visitor {
    
    func visit(qlform: QLForm) {
        print("Form -> ")
    }
    func visit(qlquestion: QLQuestion) {
        print("Question")
    }
    func visit(qlifstatement: QLIfStatement) {
        print("If-statement")
    }
    
    func visit(qlvariable: QLVariable) {
        print("-> Variable")
    }
    
    func visit(qlunaryexpression: QLUnaryExpression) {
        print("-> Unary expression")
    }
    func visit(qlnotexpression: QLNotExpression) {
        print("-> Not expression")
    }
    func visit(qlbinaryexpression: QLBinaryExpression) {
        print("-> Binary expression")
    }
    
    func visit(qlbool: QLBool) {
        print("-> Boolean")
    }
    func visit(qlstring: QLString) {
        print("-> String")
    }
    func visit(qlinteger: QLInteger) {
        print("-> Integer")
    }
    func visit(qldate: QLDate) {
        print("-> Date")
    }
    func visit(qldecimal: QLDecimal) {
        print("-> Decimal")
    }
    func visit(qlmoney: QLMoney) {
        print("-> Money")
    }
    
}
