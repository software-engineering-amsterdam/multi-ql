//
//  QLConst.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

protocol QLLiteral {
    func implode() -> Expression
}

class QLBooleanLiteral: QLLiteral {
    let bool: Bool
    
    init(bool: Bool) {
        self.bool = bool
    }
}

class QLStringLiteral: QLLiteral {
    let string: String
    
    init(string: String) {
        self.string = string
    }
}

class QLIntegerLiteral: QLLiteral {
    let integer: NSInteger
    
    init(integer: NSInteger) {
        self.integer = integer
    }
}

class QLFloatLiteral: QLLiteral {
    let float: Double
    
    init(float: Double) {
        self.float = float
    }
}


// Mark: Implode

extension QLBooleanLiteral {
    func implode() -> Expression {
        return BooleanLiteral(bool: bool)
    }
}

extension QLStringLiteral {
    func implode() -> Expression {
        return StringLiteral(string: string)
    }
}

extension QLIntegerLiteral {
    func implode() -> Expression {
        return IntegerLiteral(integer: integer)
    }
}

extension QLFloatLiteral {
    func implode() -> Expression {
        return FloatLiteral(float: float)
    }
}