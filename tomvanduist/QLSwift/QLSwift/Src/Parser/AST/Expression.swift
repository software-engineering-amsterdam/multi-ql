//
//  Expression.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation



//enum UnaryOp {
//    case Neg, Not
//}

enum BinaryOp {
    case Add, Sub, Mul, Div, Pow, Or, And, Lt, Le, Eq, Ne, Ge, Gt
}

protocol Expression: FormNode {
    var type: FormNodeType { get }
    
    func eval() -> NSValue?
}

class Identifier: Expression {
    private let _type: TypeThunk<Identifier, NSValue> = TypeThunk(IdentifierType())
    var type: FormNodeType {
        get {
            if let expression = expression {
                return expression.type
            } else {
                return UnknownType()
            }
        }
    }
    
    let id: String
    var expression: Expression?
    
    init(id: String, expression: Expression?) {
        self.id = id
        self.expression = expression
    }
    
    func eval() -> NSValue? {
        return _type.eval(self)
    }
}

class BooleanField: Expression {
    let type: FormNodeType = BooleanType()
    var value: NSValue?
    
    func eval() -> NSValue? {
        return value
    }
}

class StringField: Expression {
    let type: FormNodeType = StringType()
    var value: NSValue?
    
    func eval() -> NSValue? {
        return value
    }
}

class MoneyField: Expression {
    let type: FormNodeType = NumberType()
    let expression: Expression?
    
    init(expression: Expression?) {
        self.expression = expression
    }
    
    func eval() -> NSValue? {
        return expression?.eval()
    }
}

class StringLiteral: Expression {
    let type: FormNodeType = StringType()
    let string: String
    
    init(string: String) {
        self.string = string
    }
    
    func eval() -> NSValue? {
        return NSValue(pointer: string)
    }
}

class IntegerLiteral: Expression {
    let type: FormNodeType = NumberType()
    let integer: NSInteger
    
    init(integer: NSInteger) {
        self.integer = integer
    }
    
    func eval() -> NSValue? {
        return integer
    }
}

class FloatLiteral: Expression {
    let type: FormNodeType = NumberType()
    let float: Double
    
    init(float: Double) {
        self.float = float
    }
    
    func eval() -> NSValue? {
        return float
    }
}

class BooleanLiteral: Expression {
    let type: FormNodeType = BooleanType()
    let bool: Bool
    
    init (bool: Bool) {
        self.bool = bool
    }
    
    func eval() -> NSValue? {
        return bool
    }
}

class Prefix: Expression {
    private let _type: TypeThunk<Expression, NSValue>
    var type: FormNodeType { return op.type }
    
    let op: OperatorThunk<NSValue>
    let rhs: Expression
    
    init (op: Neg, rhs: Expression) {
        self.op = OperatorThunk(Neg())
        self.rhs = rhs
        
//        switch op {
//            case .Neg: self._type = TypeThunk(NumberType())
//            case .Not: self._type = TypeThunk(BooleanType())
//        }
    }
    
    func eval() -> NSValue? {
        switch op {
            case .Neg: return rhs.eval() as! Double * -1
            case .Not: return rhs.eval() == false
        }
    }
}

class Infix: Expression {
    private let _type: TypeThunk<Expression, NSValue>
    var type: FormNodeType { return _type }
    
    let op: BinaryOp
    let lhs, rhs: Expression
    
    init(op: BinaryOp, lhs: Expression, rhs: Expression) {
        self.op = op
        self.lhs = lhs
        self.rhs = rhs
        
        switch op {
            case .Add: self._type = TypeThunk(NumberType())
            case .Sub: self._type = TypeThunk(NumberType())
            case .Mul: self._type = TypeThunk(NumberType())
            case .Div: self._type = TypeThunk(NumberType())
            case .Pow: self._type = TypeThunk(NumberType())
            case .Eq: self._type = TypeThunk(BooleanType())
            case .Ne: self._type = TypeThunk(BooleanType())
            case .Ge: self._type = TypeThunk(BooleanType())
            case .Gt: self._type = TypeThunk(BooleanType())
            case .Le: self._type = TypeThunk(BooleanType())
            case .Lt: self._type = TypeThunk(BooleanType())
            case .And: self._type = TypeThunk(BooleanType())
            case .Or: self._type = TypeThunk(BooleanType())
        }

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