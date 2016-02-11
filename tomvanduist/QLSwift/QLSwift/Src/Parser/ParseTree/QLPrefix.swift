//
//  QLPrefix.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

protocol QLPrefix: QLExpression {
    init(rhs: QLExpression)
}

internal class QLPrefixBase {
    let rhs: QLExpression
    
    required init(rhs: QLExpression) {
        self.rhs = rhs
    }
}

class QLNeg: QLPrefixBase, QLPrefix {
}

class QLNot: QLPrefixBase, QLPrefix {
}


// Mark: Implode

extension QLNeg {
    func implode() -> Expression {
        return Prefix(op: UnaryOp.Neg, rhs: rhs.implode())
    }
}

extension QLNot {
    func implode() -> Expression {
        return Prefix(op: UnaryOp.Not, rhs: rhs.implode())
    }
}