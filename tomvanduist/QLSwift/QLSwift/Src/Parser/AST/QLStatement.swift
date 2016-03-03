//
//  QLStatement.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation

protocol QLStatement: QLNode, QLStatementVisitable {
}

class QLQuestion {
    let identifier: QLIdentifier
    let label: String
    
    init(identifier: QLIdentifier, label: String) {
        self.identifier = identifier
        self.label = label
    }
}

class QLVariableQuestion: QLQuestion, QLStatement {
    let type: QLType
    
    init(identifier: QLIdentifier, label: String, type: QLType) {
        self.type = type
        
        super.init(identifier: identifier, label: label)
    }
}

class QLComputedQuestion: QLQuestion, QLStatement {
    let expression: QLExpression
    
    init(identifier: QLIdentifier, label: String, expression: QLExpression) {
        self.expression = expression
        
        super.init(identifier: identifier, label: label)
    }
}

class QLConditional: QLStatement {
    let condition: QLExpression
    let ifBlock: QLBlock
    
    init(condition: QLExpression, ifBlock: QLBlock) {
        self.condition = condition
        self.ifBlock = ifBlock
    }
    
    func isSatisfied(context: Context) -> Bool {
        if let isSatisfied = condition.eval(context) as? Bool {
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