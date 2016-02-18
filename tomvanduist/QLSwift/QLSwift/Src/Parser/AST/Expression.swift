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
    
    func eval() -> NSValue?
}

class Identifier: Expression {
    let type: Type = Type.Id
    let id: String
    var expression: Expression?
    
    init(id: String, expression: Expression?) {
        self.id = id
        self.expression = expression
    }
    
    internal func resolveType(context: Context) -> Type {
        if let (type, _) = context.retrieve(self) {
            return type
        } else {
            return Type.Unknown
        }
    }
    
    func eval() -> NSValue? {
        return expression?.eval()
    }
}

class BooleanField: Expression {
    let type: Type = Type.Bool
    var value: NSValue?
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval() -> NSValue? {
        return value
    }
}

class StringField: Expression {
    let type: Type = Type.String
    var value: NSValue?
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
    
    func eval() -> NSValue? {
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
    
    func eval() -> NSValue? {
        return expression?.eval()
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
    
    func eval() -> NSValue? {
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
    
    func eval() -> NSValue? {
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
    
    func eval() -> NSValue? {
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
    
    func eval() -> NSValue? {
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
    
    func eval() -> NSValue? {
        switch op {
            case .Neg: return rhs.eval() as! Double * -1
            case .Not: return rhs.eval() == false
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
    
    func eval() -> NSValue? {
        switch op {
            case .Add: return (lhs.eval() as! Double) + (rhs.eval() as! Double)
            case .Sub: return (lhs.eval() as! Double) - (rhs.eval() as! Double)
            case .Mul: return (lhs.eval() as! Double) * (rhs.eval() as! Double)
            case .Div: return (lhs.eval() as! Double) / (rhs.eval() as! Double)
            case .Pow: return pow((lhs.eval() as! Double), (rhs.eval() as! Double))
            case .Eq: return lhs.eval() == rhs.eval()
            case .Ne: return lhs.eval() != rhs.eval()
            case .Ge: return (lhs.eval() as! Double) >= (rhs.eval() as! Double)
            case .Gt: return (lhs.eval() as! Double) > (rhs.eval() as! Double)
            case .Le: return (lhs.eval() as! Double) <= (rhs.eval() as! Double)
            case .Lt: return (lhs.eval() as! Double) < (rhs.eval() as! Double)
            case .And: return (lhs.eval() as! Bool) && (rhs.eval() as! Bool)
            case .Or: return (lhs.eval() as! Bool) || (rhs.eval() as! Bool)
        }
    }
}