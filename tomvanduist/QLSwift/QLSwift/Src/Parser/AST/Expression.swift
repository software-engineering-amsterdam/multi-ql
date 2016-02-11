//
//  Expression.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation

enum UnaryOp {
    case Neg, Not
}

enum BinaryOp {
    case Add, Sub, Mul, Div, Pow, Or, And, Lt, Le, Eq, Ge, Gt
}

indirect enum Expression {
    case Id(id: Identifier)
    case BooleanField
    case MoneyField(expression: Expression?)
    case StringLiteral(value: String)
    case IntegerLiteral(value: NSInteger)
    case FloatLiteral(value: Double)
    case BooleanLiteral(value: Bool)
    case UnaryOperator(op: UnaryOp, rhs: Expression)
    case BinaryOperator(op: BinaryOp, lhs: Expression, rhs: Expression)
}