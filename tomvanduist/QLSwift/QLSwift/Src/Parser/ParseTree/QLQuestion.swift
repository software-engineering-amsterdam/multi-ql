//
//  QLQuestion.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

class QLQuestion {
    let variable: QLVariable
    let stringLit: QLStringLiteral
    let expression: QLExpression
    
    init(variable: QLVariable, stringLit: QLStringLiteral, expression: QLExpression) {
        self.variable = variable
        self.stringLit = stringLit
        self.expression = expression
    }
}

extension QLQuestion {
    func implode() -> Statement {
        return Statement.Question(identifier: variable.implode(), label: stringLit.string, expression: expression.implode())
    }
}