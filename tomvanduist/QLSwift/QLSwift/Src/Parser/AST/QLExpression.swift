//
//  QLExpression.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


protocol QLExpression: QLNode {
    var type: QLType { get }
    
    func eval() -> NSObject
}

class QLIdentifier: QLExpression {
    var type: QLType {
        get {
            guard let type = expression?.type else {
                return QLUnknownType()
            }
            
            return type
        }
    }
    
    let id: String
    var expression: QLExpression?
    
    init(id: String, expression: QLExpression? = nil) {
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

class BooleanField: QLExpression {
    let type: QLType = QLBooleanType()
    var value: Bool = false
    
    func eval() -> NSObject {
        return value
    }
}

class StringField: QLExpression {
    let type: QLType = QLStringType()
    var string: NSString = ""
    
    func eval() -> NSObject {
        return string
    }
}

class MoneyField: QLExpression {
    let type: QLType = QLMoneyType()
    let expression: QLExpression?
    var value: NSInteger = 0
    
    init(expression: QLExpression? = nil) {
        self.expression = expression
    }
    
    func eval() -> NSObject {
        guard let result = expression?.eval() else {
            return value
        }
        
        return result
    }
}

class QLStringLiteral: QLExpression {
    let type: QLType = QLStringType()
    let string: String
    
    init(string: String) {
        self.string = string
    }
    
    func eval() -> NSObject {
        return string
    }
}

class QLIntegerLiteral: QLExpression {
    let type: QLType = QLMoneyType()
    let integer: NSInteger
    
    init(integer: NSInteger) {
        self.integer = integer
    }
    
    func eval() -> NSObject {
        return integer
    }
}

class QLBooleanLiteral: QLExpression {
    let type: QLType = QLBooleanType()
    let bool: Bool
    
    init (bool: Bool) {
        self.bool = bool
    }
    
    func eval() -> NSObject {
        return bool
    }
}

class QLUnary {
    private var _type: QLType = QLUnknownType()
    var type: QLType { return _type }
    
    let rhs: QLExpression
    
    required init(rhs: QLExpression) {
        self.rhs = rhs
    }
    
    func eval() -> NSObject {
        fatalError("Override")
    }
}

class QLNot: QLUnary, QLExpression {
    required init (rhs: QLExpression) {
        super.init(rhs: rhs)
        
        _type = QLBooleanType()
    }
    
    override func eval() -> NSObject {
        return rhs.eval() == false
    }
}

class QLNeg: QLUnary, QLExpression {
    required init (rhs: QLExpression) {
        super.init(rhs: rhs)
        
        _type = QLMoneyType()
    }
    
    override func eval() -> NSObject {
        return rhs.eval() as! NSInteger * -1
    }
}

class QLBinary {
    private var _type: QLType = QLUnknownType()
    var type: QLType { return _type }
    
    let lhs, rhs: QLExpression
    
    required init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func eval() -> NSObject {
        fatalError("Override")
    }
}

class QLAdd: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLMoneyType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) + (rhs.eval() as! NSInteger)
    }
}

class QLSub: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLMoneyType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) - (rhs.eval() as! NSInteger)
    }
}

class QLMul: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLMoneyType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) * (rhs.eval() as! NSInteger)
    }
}

class QLDiv: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLMoneyType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) / (rhs.eval() as! NSInteger)
    }
}

class QLPow: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLMoneyType()
    }
    
    override func eval() -> NSObject {
        return NSInteger(pow((lhs.eval() as! Double), (rhs.eval() as! Double)))
    }
}

class QLEq: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLBooleanType()
    }
    
    override func eval() -> NSObject {
        return lhs.eval() == rhs.eval()
    }
}

class QLNe: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLBooleanType()
    }
    
    override func eval() -> NSObject {
        return lhs.eval() != rhs.eval()
    }
}

class QLGe: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLBooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) >= (rhs.eval() as! NSInteger)
    }
}

class QLGt: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLBooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) > (rhs.eval() as! NSInteger)
    }
}

class QLLe: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLBooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) <= (rhs.eval() as! NSInteger)
    }
}

class QLLt: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLBooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! NSInteger) < (rhs.eval() as! NSInteger)
    }
}

class QLAnd: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLBooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! Bool) && (rhs.eval() as! Bool)
    }
}

class QLOr: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
        
        _type = QLBooleanType()
    }
    
    override func eval() -> NSObject {
        return (lhs.eval() as! Bool) || (rhs.eval() as! Bool)
    }
}