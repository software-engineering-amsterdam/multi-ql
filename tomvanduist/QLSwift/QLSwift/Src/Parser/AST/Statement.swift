//
//  Statement.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation

protocol Statement: ASTNode {
}

class Question: Statement {
    let identifier: Identifier
    let label: String
    let expression: Expression
    
    init(identifier: Identifier, label: String, expression: Expression) {
        self.identifier = identifier
        self.label = label
        self.expression = expression
    }
}

class Conditional: Statement {
    let condition: Expression
    let ifBlock: Block
    
    init(condition: Expression, ifBlock: Block) {
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

class Block: Statement {
    let block: [Statement]
    
    init (block: [Statement]) {
        self.block = block
    }
}