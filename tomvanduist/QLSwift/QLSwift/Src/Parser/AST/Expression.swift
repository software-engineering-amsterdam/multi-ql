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
}

class BooleanField: Expression {
    let type: Type = Type.Bool
    var value: NSValue?
    
    internal func resolveType(context: Context) -> Type {
        return type
    }
}

class StringField: Expression {
    let type: Type = Type.String
    var value: NSValue?
    
    internal func resolveType(context: Context) -> Type {
        return type
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
}