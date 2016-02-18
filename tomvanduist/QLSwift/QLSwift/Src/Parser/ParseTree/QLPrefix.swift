//
//  QLUnary.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

protocol QLUnary: QLExpression {
    init(rhs: QLExpression)
}

internal class QLUnaryBase {
    let rhs: QLExpression
    
    required init(rhs: QLExpression) {
        self.rhs = rhs
    }
}

class QLNeg: QLUnaryBase, QLUnary {
}

class QLNot: QLUnaryBase, QLUnary {
}
