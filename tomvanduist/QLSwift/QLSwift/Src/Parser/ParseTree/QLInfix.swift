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
    func implode() -> Expression {
        return Infix(op: BinaryOp.Add, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLSub: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Sub, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLMul: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Mul, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLDiv: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Div, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLPow: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Pow, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLAnd: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.And, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLOr: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Or, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLLt: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Lt, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLLe: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Le, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLEq: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Eq, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLGe: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Ge, lhs: lhs.implode(), rhs: rhs.implode())
    }
}

class QLGt: QLInfixBase, QLInfix {
    func implode() -> Expression {
        return Infix(op: BinaryOp.Gt, lhs: lhs.implode(), rhs: rhs.implode())
    }
}