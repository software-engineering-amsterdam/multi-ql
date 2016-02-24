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

protocol TypeEval: ExpressionType {
    typealias GenericParam
    typealias GenericReturn
    
    func eval(param: GenericParam?) -> GenericReturn?
}


class TypeThunk<P, R> : NSObject, TypeEval {
    private let _eval : (param: P?) -> R?
    
    init<T : TypeEval where T.GenericParam == P, T.GenericReturn == R>(_ dep : T) {
        _eval = dep.eval
    }
    
    func eval(param: P?) -> R? {
        return _eval(param: param)
    }
}

class IdentifierType: TypeEval {
    typealias GenericParam = Identifier
    typealias GenericReturn = NSValue
    
    func eval(identifier: Identifier?) -> NSValue? {
        return identifier?.expression?.eval()
    }
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

infix operator !== { associativity left precedence 140 }
func !== (left: ExpressionType, right: ExpressionType) -> Bool {
    return left.dynamicType != right.dynamicType
}
