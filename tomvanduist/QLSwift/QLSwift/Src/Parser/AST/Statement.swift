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
    let ifBlock: Statement
    let elseBlock: Statement?
    
    init(condition: Expression, ifBlock: Statement, elseBlock: Statement?) {
        self.condition = condition
        self.ifBlock = ifBlock
        self.elseBlock = elseBlock
    }
}

class Block: Statement {
    let block: [Statement]
    
    init (block: [Statement]) {
        self.block = block
    }
}