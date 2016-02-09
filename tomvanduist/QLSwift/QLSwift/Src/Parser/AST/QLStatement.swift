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

class QLStatementList: QLStatement {
    let statements: [QLStatement]
    
    init(statements: [QLStatement]) {
        self.statements = statements
    }
    
    override var description: String {
        var result = super.description + " {\n"
        for stmt in statements {
            result += "\t\(stmt)\n"
        }
        return result + "}"
    }
}

class QLIf: QLStatement {
    let statement: QLStatement
    let conditional: QLExpression
    
    init(conditional: QLExpression, statement: QLStatement) {
        self.statement = statement
        self.conditional = conditional
    }
    
    override var description: String { return super.description + ".conditional = \(conditional), .statement = \(statement); " }
}