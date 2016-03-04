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

class QLBooleanType: QLType {
    let defaultValue: NSObject = true
    
    func toString() -> String {
        return "boolean"
    }
    
    func accept<T: QLTypeVisitor>(visitor: T, param: T.QLTypeVisitorParam) -> T.QLTypeVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLIntegerType: QLType {
    let defaultValue: NSObject = 0
    
    func toString() -> String {
        return "integer"
    }
    
    func accept<T: QLTypeVisitor>(visitor: T, param: T.QLTypeVisitorParam) -> T.QLTypeVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLStringType: QLType {
    let defaultValue: NSObject = ""
    
    func toString() -> String {
        return "string"
    }
    
    func accept<T: QLTypeVisitor>(visitor: T, param: T.QLTypeVisitorParam) -> T.QLTypeVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLUnknownType: QLType {
    let defaultValue: NSObject = NSNull()
    
    func accept<T: QLTypeVisitor>(visitor: T, param: T.QLTypeVisitorParam) -> T.QLTypeVisitorReturn {
        return visitor.visit(self, param: param)
    }
}


// MARK: - Equatable

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
