//
//  QLTypeChecker.swift
//  QLSwift
//
//  Created by Julian Jansen on 10-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class QLTypeStack {
    var stack = Array<QLExpression.Type>()
    
    func push(element: QLExpression.Type) {
        stack.append(element)
    }
    
    func pop() -> QLExpression.Type? {
        return stack.popLast()
    }
    
    func getCurrectIndex() -> Int {
        return stack.count
    }
}

class QLTypeChecker: Visitor {
    
    let symbolTable: QLSymbolTable
    
    init(symbolTable: QLSymbolTable) {
        self.symbolTable = symbolTable
    }
    
    
    // TEST
    

    
    private var typeStack = Array<QLExpression.Type>()
    
    ///////
    
    let testCase = false
    
    enum TypeError: ErrorType {
        case A
    }
    
    func visit(qlform: QLForm) {

        for statement in qlform.codeBlock {
            statement.accept(self)
        }
    
    }

    func visit(qlquestion: QLQuestion) {
        print("Question: \(qlquestion.name)")
    }

    func visit(qlifstatement: QLIfStatement) {
        print("If-statement")
        
        qlifstatement.condition.accept(self)
        
        for statement in qlifstatement.codeBlock {
            statement.accept(self)
        }
    }
    
    func visit(qlvariable: QLVariable) {
        print("-> Variable")
    }
    
    // MARK: Expressions.
    func visit(qlunaryexpression: QLUnaryExpression) {
        print("-> Unary expression")
        qlunaryexpression.literal.accept(self)
    }
    
    func visit(qlbinaryexpression: QLBinaryExpression) {
        print("-> Binary expression")
        qlbinaryexpression.lhs.accept(self)
        qlbinaryexpression.rhs.accept(self)
    }
    
    func visit(qlnotexpression: QLNotExpression) {
        print("-> Not expression")
        qlnotexpression.accept(self)
    }
        
    func visit(qlgreaterthanexpression: QLGreaterThanExpression) {
        print("Greater than")
        qlgreaterthanexpression.lhs.accept(self)
        qlgreaterthanexpression.rhs.accept(self)
    }
    
    func visit(qlsmallerthanexpression: QLSmallerThanExpression) {
        print("Smaller than")
        qlsmallerthanexpression.lhs.accept(self)
        qlsmallerthanexpression.rhs.accept(self)
    }
    
    func visit(qlgreaterorisexpression: QLGreaterOrIsExpression) {
        print("----------------------")
        print("Greater or is")
        
        print(symbolTable.getSymbolTable().description)
        
        print(self)
        
        // Get types.
        if let lhs = qlgreaterorisexpression.lhs as? QLUnaryExpression {
            print(lhs.dynamicType)
        } else {
            qlgreaterorisexpression.lhs.accept(self)
        }
        
        
        
        qlgreaterorisexpression.rhs.accept(self)
        
        print("----------------------")

    }
    
    func visit(qlsmallerorisexpression: QLSmallerOrISExpression) {
        print("Smaller or is")
        qlsmallerorisexpression.lhs.accept(self)
        qlsmallerorisexpression.rhs.accept(self)
    }
    
    func visit(qlisnotexpression: QLIsNotExpression) {
        print("Is not")
        qlisnotexpression.lhs.accept(self)
        qlisnotexpression.rhs.accept(self)
    }
    
    func visit(qlisexpression: QLIsExpression) {
        print("Is")
        qlisexpression.lhs.accept(self)
        qlisexpression.rhs.accept(self)
    }
    
    func visit(qlmultiplyexpression: QLMultiplyExpression) {
        print("Multiply")
        qlmultiplyexpression.lhs.accept(self)
        qlmultiplyexpression.rhs.accept(self)
    }
    
    func visit(qldivideexpression: QLDivideExpression) {
        print("Divide")
        qldivideexpression.lhs.accept(self)
        qldivideexpression.rhs.accept(self)
    }
    
    func visit(qladdexpression: QLAddExpression) {
        print("Add")
        qladdexpression.lhs.accept(self)
        qladdexpression.rhs.accept(self)
    }
    
    func visit(qlsubtractexpression: QLSubtractExpression) {
        print("Substract")
        qlsubtractexpression.lhs.accept(self)
        qlsubtractexpression.rhs.accept(self)
    }
    
    func visit(qlandexpression: QLAndExpression) {
        print("And")
        qlandexpression.lhs.accept(self)
        qlandexpression.rhs.accept(self)
    }
    
    func visit(qlorexpression: QLOrExpression) {
        print("Or")
        qlorexpression.lhs.accept(self)
        qlorexpression.rhs.accept(self)
    }
    
    // MARK: Literals.
    func visit(qlunknownliteral: QLUnknownLiteral) { }
    
    func visit(qlbool: QLBool) { }
    
    func visit(qlstring: QLString) { }
    
    func visit(qlinteger: QLInteger) { }
    
    func visit(qldate: QLDate) { }
    
    func visit(qldecimal: QLDecimal) { }
    
    func visit(qlmoney: QLMoney) { }
    
}