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
