//
//  QLVisitorProtocols.swift
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
    func visit(qlbinaryexpression: QLBinaryExpression)
    func visit(qlnotexpression: QLNotExpression)
    func visit(qlgreaterthanexpression: QLGreaterThanExpression)
    func visit(qlsmallerthanexpression: QLSmallerThanExpression)
    func visit(qlgreaterorisexpression: QLGreaterOrIsExpression)
    func visit(qlsmallerorisexpression: QLSmallerOrISExpression)
    func visit(qlisnotexpression: QLIsNotExpression)
    func visit(qlisexpression: QLIsExpression)
    func visit(qlmultiplyexpression: QLMultiplyExpression)
    func visit(qldivideexpression: QLDivideExpression)
    func visit(qladdexpression: QLAddExpression)
    func visit(qlsubtractexpression: QLSubtractExpression)
    func visit(qlandexpression: QLAndExpression)
    func visit(qlorexpression: QLOrExpression)
    
    func visit(qlunknownliteral: QLUnknownLiteral)
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
