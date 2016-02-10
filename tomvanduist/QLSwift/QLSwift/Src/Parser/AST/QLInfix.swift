//
//  QLInfix.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

internal class QLInfix: QLExpression {
    let lhs, rhs: QLExpression
    
    required init(lhs: QLExpression, rhs:QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
    
    override var description: String { return super.description + ".lhs = \(lhs), .rhs = \(rhs) )" }
}

class QLAdd: QLInfix {
}

class QLSub: QLInfix {
}

class QLMul: QLInfix {
}

class QLDiv: QLInfix {
}

class QLPow: QLInfix {
}

class QLAnd: QLInfix {
}

class QLOr: QLInfix {
}

class QLLt: QLInfix {
}

class QLLe: QLInfix {
}

class QLEq: QLInfix {
}

class QLGe: QLInfix {
}

class QLGt: QLInfix {
}