//
//  Operator.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import Foundation

protocol Operator {
    var type: FormNodeType { get }
    
    typealias GenericReturn = NSValue
    
    func eval(param: Expression?) -> GenericReturn?
}

class OperatorThunk<R> : NSObject, Operator {
    private let _type: FormNodeType
    private let _eval: (param: Expression?) -> R?
    
    init<T : Operator where T.GenericReturn == R>(_ dep : T) {
        _eval = dep.eval
        _type = dep.type
    }
    
    func eval(param: Expression?) -> R? {
        return _eval(param: param)
    }
    
    var type: FormNodeType {
        return _type
    }
}

protocol UnaryOp: Operator {
    typealias GenericReturn = NSValue
}


class Neg: UnaryOp {
    let type: FormNodeType = NumberType()
    
    func eval(expression: Expression?) -> NSValue? {
        return expression!.eval() as! Double * -1
    }
}

class Not: UnaryOp {
    let type: FormNodeType = BooleanType()
    
    func eval(expression: Expression?) -> NSValue? {
        return expression?.eval() == false
    }
}
