//
//  QLExpression.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


protocol QLExpression: QLNode, QLExpressionVisitable {
    func eval(context: Context) -> NSObject?
}

class QLVariable: QLExpression {
    let id: String
    
    init(id: String) {
        self.id = id
    }
    
    func toString() -> String {
        return id
    }
    
    func eval(context: Context) -> NSObject? {
        return context.retrieve(id)
    }
    
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLLiteralExpression: QLExpression {
    let literal: QLLiteral
    
    init(literal: QLLiteral) {
        self.literal = literal
    }
    
    func toString() -> String {
        return literal.toString()
    }
    
    func eval(context: Context) -> NSObject? {
        return literal.eval(context)
    }
    
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLUnary {
    let rhs: QLExpression
    
    required init(rhs: QLExpression) {
        self.rhs = rhs
    }
    
    func toString() -> String {
        fatalError("Override")
    }
    
    func eval(context: Context) -> NSObject? {
        fatalError("Override")
    }
}

class QLNot: QLUnary, QLExpression {
    required init (rhs: QLExpression) {
        super.init(rhs: rhs)
    }
    
    override func toString() -> String {
        return "!"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let value = rhs.eval(context) as? Bool
            else { return nil }
        
        return value == false
    }
    
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLNeg: QLUnary, QLExpression {
    required init (rhs: QLExpression) {
        super.init(rhs: rhs)
    }
    
    override func toString() -> String {
        return "-"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let value = rhs.eval(context) as? NSInteger
            else { return nil }
        
        return value * -1
    }
    
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLBinary: QLExpression {
    let lhs, rhs: QLExpression
    
    required init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func toString() -> String {
        fatalError("Override")
    }
    
    func eval(context: Context) -> NSObject? {
        fatalError("Override")
    }
    
    func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        fatalError("Simulated abstract")
    }
}

class QLAdd: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "+"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? NSInteger, rval = rhs.eval(context) as? NSInteger
            else { return nil }
        
        return lval + rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLSub: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "-"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? NSInteger, rval = rhs.eval(context) as? NSInteger
            else { return nil }
        
        return lval - rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLMul: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "*"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? NSInteger, rval = rhs.eval(context) as? NSInteger
            else { return nil }
        
        return lval * rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLDiv: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "/"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? NSInteger, rval = rhs.eval(context) as? NSInteger
            else { return nil }
        
        return lval / rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLPow: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "^"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? Double, rval = rhs.eval(context) as? Double
            else { return nil }
        
        return NSInteger(pow(lval, rval))
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLEq: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "=="
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context), rval = rhs.eval(context)
            else { return nil }
        
        return lval == rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLNe: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "!="
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context), rval = rhs.eval(context)
            else { return nil }
        
        return lval != rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLGe: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return ">="
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? NSInteger, rval = rhs.eval(context) as? NSInteger
            else { return nil }
        
        return lval >= rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLGt: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return ">"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? NSInteger, rval = rhs.eval(context) as? NSInteger
            else { return nil }
        
        return lval > rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLLe: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "<="
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? NSInteger, rval = rhs.eval(context) as? NSInteger
            else { return nil }
        
        return lval <= rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLLt: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "<"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? NSInteger, rval = rhs.eval(context) as? NSInteger
            else { return nil }
        
        return lval < rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLAnd: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "&&"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? Bool, rval = rhs.eval(context) as? Bool
            else { return nil }
        
        return lval && rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLOr: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func toString() -> String {
        return "||"
    }
    
    override func eval(context: Context) -> NSObject? {
        guard let lval = lhs.eval(context) as? Bool, rval = rhs.eval(context) as? Bool
            else { return nil }
        
        return lval || rval
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}