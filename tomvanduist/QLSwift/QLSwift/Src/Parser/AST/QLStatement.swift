//
//  QLStatement.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation

protocol QLStatement: QLNode {
}

class QLQuestion: QLStatement {
    let identifier: QLIdentifier
    let label: String
    let expression: QLExpression
    
    init(identifier: QLIdentifier, label: String, expression: QLExpression) {
        self.identifier = identifier
        self.label = label
        self.expression = expression
    }
}

class QLConditional: QLStatement {
    let condition: QLExpression
    let ifBlock: QLBlock
    
    init(condition: QLExpression, ifBlock: QLBlock) {
        self.condition = condition
        self.ifBlock = ifBlock
    }
    
    func isSatisfied() -> Bool {
        if let isSatisfied = condition.eval() as? Bool {
            return isSatisfied
        }
        return false
    }
}

class QLBlock: QLStatement {
    let block: [QLStatement]
    
    init (block: [QLStatement]) {
        self.block = block
    }
}