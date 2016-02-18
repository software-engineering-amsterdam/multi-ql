//
//  Type.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import Foundation

infix operator === { associativity left precedence 140 }
func === (left: FormNodeType, right: FormNodeType) -> Bool {
    return left.dynamicType == right.dynamicType
}
infix operator !== { associativity left precedence 140 }
func !== (left: FormNodeType, right: FormNodeType) -> Bool {
    return left.dynamicType != right.dynamicType
}


protocol FormNodeType {
}

//class PlaceholderType: Type {
//}

protocol TypeEval: FormNodeType {
    typealias GenericParam
    typealias GenericReturn
    
    func eval(param: GenericParam?) -> GenericReturn?
}


class TypeThunk<P, R> : NSObject, TypeEval {
    // closure which will be used to implement `magic()` as declared in the protocol
    private let _eval : (param: P?) -> R?
    
    // `T` is effectively a handle for `AbstractType` in the protocol
    init<T : TypeEval where T.GenericParam == P, T.GenericReturn == R>(_ dep : T) {
        // requires Swift 2, otherwise create explicit closure
        _eval = dep.eval
    }
    
    func eval(param: P?) -> R? {
        // any protocol methods are implemented by forwarding
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
