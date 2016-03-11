//
//  QLLiteral.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

protocol QLLiteral: QLNode, QLLiteralVisitable {
    func eval(context: Context) -> NSObject
}

class QLStringLiteral: QLLiteral {
    let value: String
    
    init(value: String) {
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
    let value: NSInteger
    
    init(value: NSInteger) {
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
    let value: Double
    
    init(value: Double) {
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
    let value: Bool
    
    init (value: Bool) {
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