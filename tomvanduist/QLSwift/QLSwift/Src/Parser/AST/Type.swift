//
//  Type.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import Foundation


protocol FormNodeType {
}

protocol TypeEval: FormNodeType {
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

class FormType: FormNodeType {
}

class BooleanType: FormNodeType {
}

class NumberType: FormNodeType {
}

//class IntegerType: NumberType {
//}
//
//class FloatType: NumberType {
//}

class StringType: FormNodeType {
}

class UnknownType: FormNodeType {
}


infix operator === { associativity left precedence 140 }
func === (left: FormNodeType, right: FormNodeType) -> Bool {
    return left.dynamicType == right.dynamicType
}

infix operator !== { associativity left precedence 140 }
func !== (left: FormNodeType, right: FormNodeType) -> Bool {
    return left.dynamicType != right.dynamicType
}
