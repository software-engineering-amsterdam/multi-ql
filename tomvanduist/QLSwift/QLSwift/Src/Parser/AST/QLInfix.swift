//
//  QLInfix.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

internal class QLInfix: QLExpression {
    let op: String
    let lhs, rhs: QLExpression
    
    init(lhs: QLExpression, op: String, rhs:QLExpression) {
        self.lhs = lhs
        self.op = op
        self.rhs = rhs
    }
    
    override var description: String { return super.description + "( \(lhs) \(op) \(rhs) )" }
}

class QLAdd: QLInfix {
    init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, op: "+", rhs: rhs)
    }
}

class QLSub: QLInfix {
    init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, op: "-", rhs: rhs)
    }
}

class QLMul: QLInfix {
    init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, op: "*", rhs: rhs)
    }
}

class QLDiv: QLInfix {
    init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, op: "/", rhs: rhs)
    }
}

class QLPow: QLInfix {
    init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, op: "^", rhs: rhs)
    }
}

class QLAnd: QLInfix {
    init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, op: "&&", rhs: rhs)
    }
}

class QLOr: QLInfix {
    init(lhs: QLExpression, rhs: QLExpression) {
        super.init(lhs: lhs, op: "||", rhs: rhs)
    }
}