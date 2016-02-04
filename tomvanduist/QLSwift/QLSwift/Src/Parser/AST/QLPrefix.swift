//
//  QLPrefix.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

internal class QLPrefix: QLExpression {
    let op: String
    let rhs: QLExpression
    
    init(op: String, rhs: QLExpression) {
        self.op = op
        self.rhs = rhs
    }
    
    override var description: String { return super.description + "( \(op)\(rhs) )" }
}

class QLNeg: QLPrefix {
    init(rhs: QLExpression) {
        super.init(op: "-", rhs: rhs)
    }
}

class QLNot: QLPrefix {
    init(rhs: QLExpression) {
        super.init(op: "!", rhs: rhs)
    }
}