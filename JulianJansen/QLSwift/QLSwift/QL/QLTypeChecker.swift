//
//  QLTypeChecker.swift
//  QLSwift
//
//  Created by Julian Jansen on 10-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class QLExpressionTable {
    private var table = Dictionary<Int, QLLiteral.Type>()
    
    func storeExpression(expression: QLExpression, type: QLLiteral.Type) {
 
        table[expression.getID()] = type
        
        print(table.description)
    }
    
    func getExpressionType(expression: QLExpression) -> QLLiteral.Type? {

        
        if let type = table[expression.getID()] {
            return type
        } else {
            return nil
        }
    }
}


class QLTypeChecker: Visitor {
    
    var expressionTable = QLExpressionTable()
    let symbolTable: QLSymbolTable
    
    init(symbolTable: QLSymbolTable) {
        self.symbolTable = symbolTable
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
        print("-> Unary expression: \(qlunaryexpression.getID())")
        
        expressionTable.storeExpression(qlunaryexpression, type: qlunaryexpression.literal.dynamicType)
        
        qlunaryexpression.literal.accept(self)
    }
    
    func visit(qlbinaryexpression: QLBinaryExpression) {
        print("-> Binary expression: \(qlbinaryexpression.getID())")
        qlbinaryexpression.lhs.accept(self)
        qlbinaryexpression.rhs.accept(self)
    }
    
    func visit(qlnotexpression: QLNotExpression) {
        print("-> Not expression: \(qlnotexpression.getID())")
        qlnotexpression.accept(self)
    }
        
    func visit(qlgreaterthanexpression: QLGreaterThanExpression) {
        print("Greater than: \(qlgreaterthanexpression.getID())")
        qlgreaterthanexpression.lhs.accept(self)
        qlgreaterthanexpression.rhs.accept(self)
    }
    
    func visit(qlsmallerthanexpression: QLSmallerThanExpression) {
        print("Smaller than: \(qlsmallerthanexpression.getID())")
        qlsmallerthanexpression.lhs.accept(self)
        qlsmallerthanexpression.rhs.accept(self)
    }
    
    func visit(qlgreaterorisexpression: QLGreaterOrIsExpression) {
        print("----------------------")
        print("Greater or is: \(qlgreaterorisexpression.getID())")
        
        print(qlgreaterorisexpression.lhs.getID())

        
        qlgreaterorisexpression.lhs.accept(self)
        qlgreaterorisexpression.rhs.accept(self)
        
        print("Get types")
        print(expressionTable.getExpressionType(qlgreaterorisexpression.lhs))
        print(expressionTable.getExpressionType(qlgreaterorisexpression.rhs))


        
        print("----------------------")

    }
    
    func visit(qlsmallerorisexpression: QLSmallerOrISExpression) {
        print("Smaller or is: \(qlsmallerorisexpression.getID())")
        qlsmallerorisexpression.lhs.accept(self)
        qlsmallerorisexpression.rhs.accept(self)
    }
    
    func visit(qlisnotexpression: QLIsNotExpression) {
        print("Is not: \(qlisnotexpression.getID())")
        qlisnotexpression.lhs.accept(self)
        qlisnotexpression.rhs.accept(self)
    }
    
    func visit(qlisexpression: QLIsExpression) {
        print("Is: \(qlisexpression.getID())")
        qlisexpression.lhs.accept(self)
        qlisexpression.rhs.accept(self)
    }
    
    func visit(qlmultiplyexpression: QLMultiplyExpression) {
        print("Multiply: \(qlmultiplyexpression.getID())")
        qlmultiplyexpression.lhs.accept(self)
        qlmultiplyexpression.rhs.accept(self)
    }
    
    func visit(qldivideexpression: QLDivideExpression) {
        print("Divide: \(qldivideexpression.getID())")
        qldivideexpression.lhs.accept(self)
        qldivideexpression.rhs.accept(self)
    }
    
    func visit(qladdexpression: QLAddExpression) {
        print("Add: \(qladdexpression.getID())")
        qladdexpression.lhs.accept(self)
        qladdexpression.rhs.accept(self)
    }
    
    func visit(qlsubtractexpression: QLSubtractExpression) {
        print("Substract: \(qlsubtractexpression.getID())")
        qlsubtractexpression.lhs.accept(self)
        qlsubtractexpression.rhs.accept(self)
    }
    
    func visit(qlandexpression: QLAndExpression) {
        print("And: \(qlandexpression.getID())")
        qlandexpression.lhs.accept(self)
        qlandexpression.rhs.accept(self)
    }
    
    func visit(qlorexpression: QLOrExpression) {
        print("Or: \(qlorexpression.getID())")
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