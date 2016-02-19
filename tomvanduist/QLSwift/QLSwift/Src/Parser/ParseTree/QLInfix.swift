//
//  QLInfix.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

protocol QLInfix: QLExpression {
    init(lhs: QLExpression, rhs:QLExpression)
}

internal class QLInfixBase {
    let lhs, rhs: QLExpression
    
    required init(lhs: QLExpression, rhs:QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
}

class QLAdd: QLInfixBase, QLInfix {
}

class QLSub: QLInfixBase, QLInfix {
}

class QLMul: QLInfixBase, QLInfix {
}

class QLDiv: QLInfixBase, QLInfix {
}

class QLPow: QLInfixBase, QLInfix {
}

class QLAnd: QLInfixBase, QLInfix {
}

class QLOr: QLInfixBase, QLInfix {
}

class QLLt: QLInfixBase, QLInfix {
}

class QLLe: QLInfixBase, QLInfix {
}

class QLEq: QLInfixBase, QLInfix {
}

class QLNe: QLInfixBase, QLInfix {
}

class QLGe: QLInfixBase, QLInfix {
}

class QLGt: QLInfixBase, QLInfix {
}