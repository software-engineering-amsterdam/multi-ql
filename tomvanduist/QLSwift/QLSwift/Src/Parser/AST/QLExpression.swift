//
//  QLExpression.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


protocol QLExpression: QLNode, QLExpressionVisitable {
    func eval(context: QLContext) -> NSObject
}

class QLVariable: QLExpression {
    let id: String
    
    init(id: String) {
        self.id = id
    }
    
    func eval(context: QLContext) -> NSObject {
        return context.retrieve(id)
    }
}

class QLLiteralExpression: QLExpression {
    let literal: QLLiteral
    
    init(literal: QLLiteral) {
        self.literal = literal
    }
    
    func eval(context: QLContext) -> NSObject {
        return literal.eval(context)
    }
}

class QLUnary {
    let rhs: QLExpression
    
    required init(rhs: QLExpression) {
        self.rhs = rhs
    }
    
    func eval(context: QLContext) -> NSObject {
        fatalError("Override")
    }
}

class QLNot: QLUnary, QLExpression {
    required init (rhs: QLExpression) {
        super.init(rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return rhs.eval(context) == false
    }
}

class QLNeg: QLUnary, QLExpression {
    required init (rhs: QLExpression) {
        super.init(rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return rhs.eval(context) as! NSInteger * -1
    }
}

class QLBinary: QLExpression {
    let lhs, rhs: QLExpression
    
    required init(lhs: QLExpression, rhs: QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    func eval(context: QLContext) -> NSObject {
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
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! NSInteger) + (rhs.eval(context) as! NSInteger)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLSub: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! NSInteger) - (rhs.eval(context) as! NSInteger)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLMul: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! NSInteger) * (rhs.eval(context) as! NSInteger)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLDiv: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! NSInteger) / (rhs.eval(context) as! NSInteger)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLPow: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return NSInteger(pow((lhs.eval(context) as! Double), (rhs.eval(context) as! Double)))
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLEq: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return lhs.eval(context) == rhs.eval(context)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLNe: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return lhs.eval(context) != rhs.eval(context)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLGe: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! NSInteger) >= (rhs.eval(context) as! NSInteger)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLGt: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! NSInteger) > (rhs.eval(context) as! NSInteger)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLLe: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! NSInteger) <= (rhs.eval(context) as! NSInteger)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLLt: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! NSInteger) < (rhs.eval(context) as! NSInteger)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLAnd: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! Bool) && (rhs.eval(context) as! Bool)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLOr: QLBinary {
    required init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, rhs: rhs)
    }
    
    override func eval(context: QLContext) -> NSObject {
        return (lhs.eval(context) as! Bool) || (rhs.eval(context) as! Bool)
    }
    
    override func accept<T: QLExpressionVisitor>(visitor: T, param: T.QLExpressionVisitorParam) -> T.QLExpressionVisitorReturn {
        return visitor.visit(self, param: param)
    }
}