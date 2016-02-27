//
//  Type.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import Foundation


protocol ExpressionType {
}

class IdentifierType: ExpressionType {
}

class FormType: ExpressionType {
}

class BooleanType: ExpressionType {
}

class NumberType: ExpressionType {
}

class StringType: ExpressionType {
}

class UnknownType: ExpressionType {
}


infix operator === { associativity left precedence 140 }
func === (left: ExpressionType, right: ExpressionType) -> Bool {
    return left.dynamicType == right.dynamicType
}
func === (left: ExpressionType, right: ExpressionType.Type) -> Bool {
    return left.dynamicType == right.self
}
func === (left: ExpressionType.Type, right: ExpressionType) -> Bool {
    return right === left
}

infix operator !== { associativity left precedence 140 }
func !== (left: ExpressionType, right: ExpressionType) -> Bool {
    return left.dynamicType != right.dynamicType
}
func !== (left: ExpressionType, right: ExpressionType.Type) -> Bool {
    return left.dynamicType != right.self
}
func !== (left: ExpressionType.Type, right: ExpressionType) -> Bool {
    return right !== left
}
