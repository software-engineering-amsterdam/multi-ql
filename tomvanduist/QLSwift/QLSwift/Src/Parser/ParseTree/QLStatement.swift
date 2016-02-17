//
//  File.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation

protocol QLStatement {
    func implode() -> Statement
}

class QLQuestionStatement: QLStatement {
    let question: QLQuestion
    
    init(question: QLQuestion) {
        self.question = question
    }
}

class QLBlockStatement: QLStatement {
    let block: [QLStatement]
    
    init(block: [QLStatement]) {
        self.block = block
    }
}

class QLIf: QLStatement {
    let block: QLBlockStatement
    let conditional: QLExpression
    
    init(conditional: QLExpression, block: QLBlockStatement) {
        self.block = block
        self.conditional = conditional
    }
}


// Mark: Implode

extension QLQuestionStatement {
    func implode() -> Statement {
        return question.implode()
    }
}

extension QLBlockStatement {
    func implode() -> Statement {
        var block: [Statement] = []
        for statement in self.block {
            block.append(statement.implode())
        }
        return Block(block: block)
    }
}

extension QLIf {
    func implode() -> Statement {
        return Conditional(condition: conditional.implode(), ifBlock: block.implode(), elseBlock: nil)
    }
}