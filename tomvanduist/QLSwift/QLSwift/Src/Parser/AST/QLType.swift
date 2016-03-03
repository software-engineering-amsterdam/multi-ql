//
//  QLType.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import Foundation


protocol QLType: QLTypeVisitable {
}

//class QLIdentifierType: QLType {
//}

class QLBooleanType: QLType {
}

class QLMoneyType: QLType {
}

class QLStringType: QLType {
}

//class QLUnknownType: QLType {
//}


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
