//
//  QLInterpreter.swift
//  QLSwift
//
//  Created by Julian Jansen on 07-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class QLExpressionEvaluator: ExpressionVisitor {
    
    private let form: Form
    
    init(form: Form) {
        self.form = form
    }
    
    func visit(qlvariable: QLVariable) -> AnyObject {
        var literal = form.getValue(qlvariable.name)
        
        if (literal.type == .Integer) {
            let temp = form.getValue(qlvariable.name) as! QLIntegerLiteral
            return temp.value
        } else if (literal.type == .Boolean) {
            let temp = form.getValue(qlvariable.name) as! QLBoolLiteral
            return temp.value
        } else {
            let temp = form.getValue(qlvariable.name) as! QLStringLiteral
            return temp.value
        }
    }
    
    func visit(expr: QLNotExpression) -> Bool {
        let value = expr.expression.accept(self) as! Bool
        return !value
    }
    
    func visit(expr: QLGreaterThanExpression) -> Bool {
        let lhsValue = expr.lhs.accept(self) as! Int
        let rhsValue = expr.rhs.accept(self) as! Int
        
        return lhsValue > rhsValue
    }
    
    func visit(expr: QLSmallerThanExpression) -> Bool {
        let lhsValue = expr.lhs.accept(self) as! Int
        let rhsValue = expr.rhs.accept(self) as! Int
        
        return lhsValue < rhsValue
    }
    
    func visit(expr: QLGreaterOrIsExpression) -> Bool {
        let lhsValue = expr.lhs.accept(self) as! Int
        let rhsValue = expr.rhs.accept(self) as! Int
        
        return lhsValue >= rhsValue
    }
    
    func visit(expr: QLSmallerOrIsExpression) -> Bool {
        let lhsValue = expr.lhs.accept(self) as! Int
        let rhsValue = expr.rhs.accept(self) as! Int
        
        return lhsValue <= rhsValue
    }
    
    func visit(expr: QLIsNotExpression) -> Bool {
        if let lhsValue = expr.lhs.accept(self) as? String {
            let rhsValue = expr.rhs.accept(self) as! String
            return lhsValue != rhsValue
        } else {
            let lhsValue = expr.lhs.accept(self) as! Int
            let rhsValue = expr.rhs.accept(self) as! Int
            return lhsValue != rhsValue
        }
    }
    
    func visit(expr: QLIsExpression) -> Bool {
        if let lhsValue = expr.lhs.accept(self) as? String {
            let rhsValue = expr.rhs.accept(self) as! String
            return lhsValue == rhsValue
        } else {
            let lhsValue = expr.lhs.accept(self) as! Int
            let rhsValue = expr.rhs.accept(self) as! Int
            return lhsValue == rhsValue
        }
    }
    
    func visit(expr: QLMultiplyExpression) -> Int {
        let lhsValue = expr.lhs.accept(self) as! Int
        let rhsValue = expr.rhs.accept(self) as! Int
        
        return lhsValue * rhsValue
    }
    
    func visit(expr: QLDivideExpression) -> Int {
        let lhsValue = expr.lhs.accept(self) as! Int
        let rhsValue = expr.rhs.accept(self) as! Int
        
        return lhsValue / rhsValue
    }
    
    func visit(expr: QLAddExpression) -> Int {
        let lhsValue = expr.lhs.accept(self) as! Int
        let rhsValue = expr.rhs.accept(self) as! Int
        
        return lhsValue + rhsValue
    }
    
    func visit(expr: QLSubtractExpression) -> Int {
        let lhsValue = expr.lhs.accept(self) as! Int
        let rhsValue = expr.rhs.accept(self) as! Int
        
        return lhsValue - rhsValue
    }
    
    func visit(expr: QLAndExpression) -> Bool {
        let lhsValue = expr.lhs.accept(self) as! Bool
        let rhsValue = expr.rhs.accept(self) as! Bool
        
        return lhsValue && rhsValue
    }
    
    func visit(expr: QLOrExpression) -> Bool {
        let lhsValue = expr.lhs.accept(self) as! Bool
        let rhsValue = expr.rhs.accept(self) as! Bool
        
        return lhsValue || rhsValue
    }
    
    // MARK: Literals
    
    func visit(qlbool: QLBoolLiteral) -> Bool {
        return qlbool.value
    }
    
    func visit(qlstring: QLStringLiteral) -> String {
        return qlstring.value
    }
    
    func visit(qlinteger: QLIntegerLiteral) -> Int {
        return qlinteger.value
    }
}