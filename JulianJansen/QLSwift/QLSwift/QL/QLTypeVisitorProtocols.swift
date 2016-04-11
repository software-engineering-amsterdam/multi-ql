//
//  QLTypeVisitorProtocols.swift
//  QLSwift
//
//  Created by Julian Jansen on 10-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol TypeVisitor {
    
    func visit(qlform: QLForm) -> QLUnknownType
    func visit(qlquestion: QLQuestion) -> QLUnknownType
    func visit(qlifstatement: QLIfStatement) -> QLUnknownType
    
    func visit(qlvariable: QLVariable) -> QLType
    
    func visit(expr: QLNotExpression) -> QLBoolType
    func visit(expr: QLGreaterThanExpression) -> QLBoolType
    func visit(expr: QLSmallerThanExpression) -> QLBoolType
    func visit(expr: QLGreaterOrIsExpression) -> QLBoolType
    func visit(expr: QLSmallerOrIsExpression) -> QLBoolType
    func visit(expr: QLIsNotExpression) -> QLBoolType
    func visit(expr: QLIsExpression) -> QLBoolType
    
    func visit(expr: QLMultiplyExpression) -> QLIntegerType
    func visit(expr: QLDivideExpression) -> QLIntegerType
    func visit(expr: QLAddExpression) -> QLIntegerType
    func visit(expr: QLSubtractExpression) -> QLIntegerType
    
    func visit(expr: QLAndExpression) -> QLBoolType
    func visit(expr: QLOrExpression) -> QLBoolType

    func visit(qlbool: QLBoolLiteral) -> QLBoolType
    func visit(qlstring: QLStringLiteral) -> QLStringType
    func visit(qlinteger: QLIntegerLiteral) -> QLIntegerType
}

protocol TypeVisitable {
    func accept(visitor: TypeVisitor) -> QLType
}
