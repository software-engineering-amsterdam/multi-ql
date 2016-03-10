//
//  QLTypeChecker.swift
//  QLSwift
//
//  Created by Julian Jansen on 10-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class QLTypeChecker: Visitor {
    
    func visit(qlform: QLForm) {
        print("Form -> ")
    }
    func visit(qlquestion: QLQuestion) {
        print("Question: \(qlquestion.name)")
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
    
    
    func visit(qlgreaterthanexpression: QLGreaterThanExpression) {
        print("Greater than")
    }
    func visit(qlsmallerthanexpression: QLSmallerThanExpression) {
        print("Smaller than")
    }
    func visit(qlgreaterorisexpression: QLGreaterOrIsExpression) {
        print("Greater or is")
    }
    func visit(qlsmallerorisexpression: QLSmallerOrISExpression) {
        print("Smaller or is")
    }
    func visit(qlisnotexpression: QLIsNotExpression) {
        print("Is not")
    }
    func visit(qlisexpression: QLIsExpression) {
        print("Is")
    }
    func visit(qlmultiplyexpression: QLMultiplyExpression) {
        print("Multiply")
    }
    func visit(qldivideexpression: QLDivideExpression) {
        print("Divide")
    }
    func visit(qladdexpression: QLAddExpression) {
        print("Add")
    }
    func visit(qlsubtractexpression: QLSubtractExpression) {
        print("Substract")
    }
    func visit(qlandexpression: QLAndExpression) {
        print("And")
    }
    func visit(qlorexpression: QLOrExpression) {
        print("Or")
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