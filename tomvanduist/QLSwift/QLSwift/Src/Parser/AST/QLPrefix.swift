//
//  QLPrefix.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

internal class QLPrefix: QLExpression {
    let rhs: QLExpression
    
    required init(rhs: QLExpression) {
        self.rhs = rhs
    }
    
    override var description: String { return super.description + "( \(rhs) )" }
}

class QLNeg: QLPrefix {
}

class QLNot: QLPrefix {
}