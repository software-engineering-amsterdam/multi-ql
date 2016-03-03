//
//  QLExpression.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


protocol QLExpression: QLNode, QLExpressionVisitable {
    func eval(context: Context) -> NSObject
}

class QLIdentifier: QLExpression {
    let id: String
    
    init(id: String) {
        self.id = id
    }
    
    func eval(context: Context) -> NSObject {
        guard let expression = context.retrieveExpression(self)
            else { return NSNull() }
        
        return expression.eval(context)
    }
}

class QLStringLiteral: QLExpression {
    let string: String
    
    init(string: String) {
        self.string = string
    }
    
    func eval(context: Context) -> NSObject {
        return string
    }
}

class QLIntegerLiteral: QLExpression {
    let integer: NSInteger
    
    init(integer: NSInteger) {
        self.integer = integer
    }
    
    func eval(context: Context) -> NSObject {
        return integer
    }
}

class QLBooleanLiteral: QLExpression {
    let bool: Bool
    
    init (bool: Bool) {
        self.bool = bool
    }
    
    func eval(context: Context) -> NSObject {
        return bool
    }
}

class QLUnary {
    let rhs: QLExpression
    
    required init(rhs: QLExpression) {
        self.rhs = rhs
    }
    
    func eval(context: Context) -> NSObject {
        fatalError("Override")
    }
}

class QLNot: QLUnary, QLExpression {
    required init (rhs: QLExpression) {
        super.init(rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return rhs.eval(context) == false
    }
}

class QLNeg: QLUnary, QLExpression {
    required init (rhs: QLExpression) {
        super.init(rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return rhs.eval(context) as! NSInteger * -1
    }
}

class QLBinary {
    let lhs, rhs: QLExpression
    
    required init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func eval(context: Context) -> NSObject {
        fatalError("Override")
    }
}

class QLAdd: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! NSInteger) + (rhs.eval(context) as! NSInteger)
    }
}

class QLSub: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! NSInteger) - (rhs.eval(context) as! NSInteger)
    }
}

class QLMul: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! NSInteger) * (rhs.eval(context) as! NSInteger)
    }
}

class QLDiv: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! NSInteger) / (rhs.eval(context) as! NSInteger)
    }
}

class QLPow: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return NSInteger(pow((lhs.eval(context) as! Double), (rhs.eval(context) as! Double)))
    }
}

class QLEq: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return lhs.eval(context) == rhs.eval(context)
    }
}

class QLNe: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return lhs.eval(context) != rhs.eval(context)
    }
}

class QLGe: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! NSInteger) >= (rhs.eval(context) as! NSInteger)
    }
}

class QLGt: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! NSInteger) > (rhs.eval(context) as! NSInteger)
    }
}

class QLLe: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! NSInteger) <= (rhs.eval(context) as! NSInteger)
    }
}

class QLLt: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! NSInteger) < (rhs.eval(context) as! NSInteger)
    }
}

class QLAnd: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! Bool) && (rhs.eval(context) as! Bool)
    }
}

class QLOr: QLBinary, QLExpression {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: Context) -> NSObject {
        return (lhs.eval(context) as! Bool) || (rhs.eval(context) as! Bool)
    }
}