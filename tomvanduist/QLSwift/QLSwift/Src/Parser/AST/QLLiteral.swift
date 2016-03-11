//
//  QLLiteral.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

typealias QLFLoat   = Double
typealias QLString  = String
typealias QLInteger = NSInteger
typealias QLBoolean = Bool


protocol QLLiteral: QLNode, QLLiteralVisitable {
    func eval(context: Context) -> NSObject // TODO: remove
}

class QLStringLiteral: QLLiteral {
    let value: QLString
    
    init(value: QLString) {
        self.value = value
    }
    
    func toString() -> String {
        return "String"
    }
    
    func eval(context: Context) -> NSObject {
        return value
    }
    
    func accept<T: QLLiteralVisitor>(visitor: T, param: T.QLLiteralVisitorParam) -> T.QLLiteralVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLIntegerLiteral: QLLiteral {
    let value: QLInteger
    
    init(value: QLInteger) {
        self.value = value
    }
    
    func toString() -> String {
        return "\(value)"
    }
    
    func eval(context: Context) -> NSObject {
        return value
    }
    
    func accept<T: QLLiteralVisitor>(visitor: T, param: T.QLLiteralVisitorParam) -> T.QLLiteralVisitorReturn {
        return visitor.visit(self, param: param)
    }
}

class QLFloatLiteral: QLLiteral {
    let value: QLFLoat
    
    init(value: QLFLoat) {
        self.value = value
    }
    
    func toString() -> String {
        return "\(value)"
    }
    
    func eval(context: Context) -> NSObject {
        return value
    }
    
    func accept<T: QLLiteralVisitor>(visitor: T, param: T.QLLiteralVisitorParam) -> T.QLLiteralVisitorReturn {
        return visitor.visit(self, param: param)
    }
}


class QLBooleanLiteral: QLLiteral {
    let value: QLBoolean
    
    init (value: QLBoolean) {
        self.value = value
    }
    
    func toString() -> String {
        return "Bool"
    }
    
    func eval(context: Context) -> NSObject {
        return value
    }
    
    func accept<T: QLLiteralVisitor>(visitor: T, param: T.QLLiteralVisitorParam) -> T.QLLiteralVisitorReturn {
        return visitor.visit(self, param: param)
    }
}