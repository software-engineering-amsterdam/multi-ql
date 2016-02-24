//
//  QLBinary.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

protocol QLBinary: QLExpression {
    init(lhs: QLExpression, rhs:QLExpression)
}

internal class QLBinaryBase {
    let lhs, rhs: QLExpression
    
    required init(lhs: QLExpression, rhs:QLExpression) {
        self.lhs = lhs
        self.rhs = rhs
    }
}

class QLAdd: QLBinaryBase, QLBinary {
}

class QLSub: QLBinaryBase, QLBinary {
}

class QLMul: QLBinaryBase, QLBinary {
}

class QLDiv: QLBinaryBase, QLBinary {
}

class QLPow: QLBinaryBase, QLBinary {
}

class QLAnd: QLBinaryBase, QLBinary {
}

class QLOr: QLBinaryBase, QLBinary {
}

class QLLt: QLBinaryBase, QLBinary {
}

class QLLe: QLBinaryBase, QLBinary {
}

class QLEq: QLBinaryBase, QLBinary {
}

class QLNe: QLBinaryBase, QLBinary {
}

class QLGe: QLBinaryBase, QLBinary {
}

class QLGt: QLBinaryBase, QLBinary {
}