//
//  Expression.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


protocol Expression: ASTNode {
    var type: ExpressionType { get }
    
    func eval() -> NSObject
}

class Identifier: Expression {
    var type: ExpressionType {
        get {
            guard let type = expression?.type else {
                return UnknownType()
            }
            
            return type
        }
    }
    
    let id: String
    var expression: Expression?
    
    init(id: String, expression: Expression? = nil) {
        self.id = id
        self.expression = expression
    }
    
    func eval() -> NSObject {
        guard let result = expression?.eval() else {
            return NSNull()
        }
        
        return result
    }
}

class BooleanField: Expression {
    let type: ExpressionType = BooleanType()
    var value: Bool = false
    
    func eval() -> NSObject {
        return value
    }
}

class StringField: Expression {
    let type: ExpressionType = StringType()
    var string: NSString = ""
    
    func eval() -> NSObject {
        return string
    }
}

class MoneyField: Expression {
    let type: ExpressionType = NumberType()
    let expression: Expression?
    let value: NSInteger = 0
    
    init(expression: Expression? = nil) {
        self.expression = expression
    }
    
    func eval() -> NSObject {
        guard let result = expression?.eval() else {
            return value
        }
        
        return result
    }
}

class StringLiteral: Expression {
    let type: ExpressionType = StringType()
    let string: String
    
    init(string: String) {
        self.string = string
    }
    
    func eval() -> NSObject {
        return string
    }
}

class IntegerLiteral: Expression {
    let type: ExpressionType = NumberType()
    let integer: NSInteger
    
    init(integer: NSInteger) {
        self.integer = integer
    }
    
    func eval() -> NSObject {
        return integer
    }
}

class BooleanLiteral: Expression {
    let type: ExpressionType = BooleanType()
    let bool: Bool
    
    init (bool: Bool) {
        self.bool = bool
    }
    
    func eval() -> NSObject {
        return bool
    }
}

class Unary {
    private var _type: ExpressionType = UnknownType()
    var type: ExpressionType { return _type }
    
    let rhs: Expression
    
    required init(rhs: Expression) {
        self.rhs = rhs
    }
    
    func eval() -> NSObject {
        fatalError("Override")
    }
}

class Not: Unary, Expression {
    required init (rhs: Expression) {
        super.init(rhs: rhs)
        
        _type = BooleanType()
    }
    
    override func eval() -> NSObject {
        return rhs.eval() == false
    }
}

class Neg: Unary, Expression {
    required init (rhs: Expression) {
        super.init(rhs: rhs)
        
        _type = NumberType()
    }
    
    override func eval() -> NSObject {
        return rhs.eval() as! NSInteger * -1
    }
}

class Binary {
    private var _type: ExpressionType = UnknownType()
    var type: ExpressionType { return _type }
    
    let lhs, rhs: Expression
    
    required init(lhs: Expression, rhs: Expression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func eval() -> NSObject {
        fatalError("Override")
    }
}

class Add: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = NumberType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) + (rhs.eval() as! NSInteger)
    }
}

class Sub: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = NumberType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) - (rhs.eval() as! NSInteger)
    }
}

class Mul: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = NumberType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) * (rhs.eval() as! NSInteger)
    }
}

class Div: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = NumberType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) / (rhs.eval() as! NSInteger)
    }
}

class Pow: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = NumberType()
    }
    
    override func eval() -> NSObject {
        return NSInteger(pow((lhs.eval() as! Double), (rhs.eval() as! Double)))
    }
}

class Eq: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = BooleanType()
    }
    
    override func eval() -> NSObject {
        return lhs.eval() == rhs.eval()
    }
}

class Ne: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = BooleanType()
    }
    
    override func eval() -> NSObject {
        return lhs.eval() != rhs.eval()
    }
}

class Ge: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = BooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) >= (rhs.eval() as! NSInteger)
    }
}

class Gt: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = BooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) > (rhs.eval() as! NSInteger)
    }
}

class Le: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = BooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) <= (rhs.eval() as! NSInteger)
    }
}

class Lt: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = BooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) < (rhs.eval() as! NSInteger)
    }
}

class And: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = BooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! Bool) && (rhs.eval() as! Bool)
    }
}

class Or: Binary, Expression {
    required init(lhs: Expression, rhs: Expression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = BooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! Bool) || (rhs.eval() as! Bool)
    }
}