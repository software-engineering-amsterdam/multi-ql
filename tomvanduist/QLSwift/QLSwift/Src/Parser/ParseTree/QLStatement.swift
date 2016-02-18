//
//  File.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation

protocol QLStatement {
    func implode(context: Context) -> Statement
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
