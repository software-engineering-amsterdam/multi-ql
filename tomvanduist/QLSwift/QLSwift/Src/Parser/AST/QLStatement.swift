//
//  File.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation

class QLStatement: NSObject {
}

class QLQuestionStatement: QLStatement {
    let question: QLQuestion
    
    init(question: QLQuestion) {
        self.question = question
    }
    
    override var description: String { return super.description + ".question(\n\t\(question));" }
}

class QLBlockStatement: QLStatement {
    let block: [QLStatement]
    
    init(block: [QLStatement]) {
        self.block = block
    }
    
    override var description: String {
        var result = super.description + " {\n"
        for stmt in block {
            result += "\t\(stmt)\n"
        }
        return result + "}"
    }
}

class QLIf: QLStatement {
    let block: QLBlockStatement
    let conditional: QLExpression
    
    init(conditional: QLExpression, block: QLBlockStatement) {
        self.block = block
        self.conditional = conditional
    }
    
    override var description: String { return super.description + ".conditional = \(conditional), .block = \(block); " }
}