//
//  QLExpressionVisitorProtocols.swift
//  QLSwift
//
//  Created by Julian Jansen on 07-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol ExpressionVisitor {
    
    func visit(qlvariable: QLVariable) -> AnyObject
    
    func visit(expr: QLNotExpression) -> Bool
    func visit(expr: QLGreaterThanExpression) -> Bool
    func visit(expr: QLSmallerThanExpression) -> Bool
    func visit(expr: QLGreaterOrIsExpression) -> Bool
    func visit(expr: QLSmallerOrIsExpression) -> Bool
    func visit(expr: QLIsNotExpression) -> Bool
    func visit(expr: QLIsExpression) -> Bool
    
    func visit(expr: QLMultiplyExpression) -> Int
    func visit(expr: QLDivideExpression) -> Int
    func visit(expr: QLAddExpression) -> Int
    func visit(expr: QLSubtractExpression) -> Int
    
    func visit(expr: QLAndExpression) -> Bool
    func visit(expr: QLOrExpression) -> Bool
    
    func visit(qlbool: QLBoolLiteral) -> Bool
    func visit(qlstring: QLStringLiteral) -> String
    func visit(qlinteger: QLIntegerLiteral) -> Int
}

protocol ExpressionVisitable {
    func accept(visitor: ExpressionVisitor) -> AnyObject
}