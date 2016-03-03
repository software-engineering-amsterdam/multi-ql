//
//  QLLiteral.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

protocol QLLiteral: QLNode, QLLiteralVisitable {
    func eval(context: QLContext) -> NSObject
}

class QLStringLiteral: QLLiteral {
    let string: String
    
    init(string: String) {
        self.string = string
    }
    
    func eval(context: QLContext) -> NSObject {
        return string
    }
}

class QLIntegerLiteral: QLLiteral {
    let integer: NSInteger
    
    init(integer: NSInteger) {
        self.integer = integer
    }
    
    func eval(context: QLContext) -> NSObject {
        return integer
    }
}

class QLBooleanLiteral: QLLiteral {
    let bool: Bool
    
    init (bool: Bool) {
        self.bool = bool
    }
    
    func eval(context: QLContext) -> NSObject {
        return bool
    }
}