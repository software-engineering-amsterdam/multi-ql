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
    case Add, Sub, Mul, Div, Pow, Or, And, Lt, Le, Eq, Ne, Ge, Gt
}

protocol Expression: FormNode {
    var type: Type { get }
    func resolveType(context: Context) -> Type
    
    func eval(context: Context) -> NSValue?
}

class Identifier: Expression {
    let type: Type = Type.Id
    let id: String
    
    init(id: String) {
        self.id = id
    }
    
    internal func resolveType(context: Context) -> Type {
        if let (type, _) = context.retrieve(self) {
            return type
        } else {
            return Type.Unknown
        }
    }
    
    func eval(context: Context) -> NSValue? {
        if let (_, expression) = context.retrieve(self) {
            return expression?.eval(context)
        }
        return nil
    }
}

class BooleanField: Expression {
    let type: Type = Type.Bool
    var value: NSValue?
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval(context: Context) -> NSValue? {
        return value
    }
}

class StringField: Expression {
    let type: Type = Type.String
    var value: NSValue?
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval(context: Context) -> NSValue? {
        return value
    }
}

class MoneyField: Expression {
    let type: Type = Type.Number
    let expression: Expression?
    
    init(expression: Expression?) {
        self.expression = expression
    }
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval(context: Context) -> NSValue? {
        return expression?.eval(context)
    }
}

class StringLiteral: Expression {
    let type: Type = Type.String
    let string: String
    
    init(string: String) {
        self.string = string
    }
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval(context: Context) -> NSValue? {
        return NSValue(pointer: string)
    }
}

class IntegerLiteral: Expression {
    let type: Type = Type.Number
    let integer: NSInteger
    
    init(integer: NSInteger) {
        self.integer = integer
    }
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval(context: Context) -> NSValue? {
        return integer
    }
}

class FloatLiteral: Expression {
    let type: Type = Type.Number
    let float: Double
    
    init(float: Double) {
        self.float = float
    }
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval(context: Context) -> NSValue? {
        return float
    }
}

class BooleanLiteral: Expression {
    let type: Type = Type.Bool
    let bool: Bool
    
    init (bool: Bool) {
        self.bool = bool
    }
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval(context: Context) -> NSValue? {
        return bool
    }
}

class Prefix: Expression {
    let op: UnaryOp
    let rhs: Expression
    
    init (op: UnaryOp, rhs: Expression) {
        self.op = op
        self.rhs = rhs
    }
    
    var type: Type {
        get {
            switch op {
                case .Neg: return Type.Number
                case .Not: return Type.Bool
            }
        }
    }
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval(context: Context) -> NSValue? {
        switch op {
            case .Neg: return rhs.eval(context) as! Double * -1
            case .Not: return rhs.eval(context) == false
        }
    }
}

class Infix: Expression {
    let op: BinaryOp
    let lhs, rhs: Expression
    
    init(op: BinaryOp, lhs: Expression, rhs: Expression) {
        self.op = op
        self.lhs = lhs
        self.rhs = rhs
    }
    
    var type: Type {
        get {
            switch op {
                case .Add: return Type.Number
                case .Sub: return Type.Number
                case .Mul: return Type.Number
                case .Div: return Type.Number
                case .Pow: return Type.Number
                case .Eq: return Type.Bool
                case .Ne: return Type.Bool
                case .Ge: return Type.Bool
                case .Gt: return Type.Bool
                case .Le: return Type.Bool
                case .Lt: return Type.Bool
                case .And: return Type.Bool
                case .Or: return Type.Bool
            }
        }
    }
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval(context: Context) -> NSValue? {
        switch op {
            case .Add: return (lhs.eval(context) as! Double) + (rhs.eval(context) as! Double)
            case .Sub: return (lhs.eval(context) as! Double) - (rhs.eval(context) as! Double)
            case .Mul: return (lhs.eval(context) as! Double) * (rhs.eval(context) as! Double)
            case .Div: return (lhs.eval(context) as! Double) / (rhs.eval(context) as! Double)
            case .Pow: return pow((lhs.eval(context) as! Double), (rhs.eval(context) as! Double))
            case .Eq: return lhs.eval(context) == rhs.eval(context)
            case .Ne: return lhs.eval(context) != rhs.eval(context)
            case .Ge: return (lhs.eval(context) as! Double) >= (rhs.eval(context) as! Double)
            case .Gt: return (lhs.eval(context) as! Double) > (rhs.eval(context) as! Double)
            case .Le: return (lhs.eval(context) as! Double) <= (rhs.eval(context) as! Double)
            case .Lt: return (lhs.eval(context) as! Double) < (rhs.eval(context) as! Double)
            case .And: return (lhs.eval(context) as! Bool) && (rhs.eval(context) as! Bool)
            case .Or: return (lhs.eval(context) as! Bool) || (rhs.eval(context) as! Bool)
        }
    }
}