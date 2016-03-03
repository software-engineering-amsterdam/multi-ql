//
//  QLType.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import Foundation


protocol QLType: QLTypeVisitable {
    var defaultValue: NSObject { get }
}

//class QLIdentifierType: QLType {
//}

class QLBooleanType: QLType {
    let defaultValue: NSObject = true
}

class QLIntegerType: QLType {
    let defaultValue: NSObject = 0
}

class QLStringType: QLType {
    let defaultValue: NSObject = ""
}

class QLUnknownType: QLType {
    let defaultValue: NSObject = NSNull()
}


infix operator === { associativity left precedence 140 }
func === (left: QLType, right: QLType) -> Bool {
    return left.dynamicType == right.dynamicType
}
func === (left: QLType, right: QLType.Type) -> Bool {
    return left.dynamicType == right.self
}
func === (left: QLType.Type, right: QLType) -> Bool {
    return right === left
}

infix operator !== { associativity left precedence 140 }
func !== (left: QLType, right: QLType) -> Bool {
    return left.dynamicType != right.dynamicType
}
func !== (left: QLType, right: QLType.Type) -> Bool {
    return left.dynamicType != right.self
}
func !== (left: QLType.Type, right: QLType) -> Bool {
    return right !== left
}
