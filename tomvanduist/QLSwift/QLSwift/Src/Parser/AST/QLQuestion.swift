//
//  QLQuestion.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

class QLQuestion: NSObject {
    let variable: QLVariable
    let string: QLStringLiteral
    let expression: QLExpression
    
    init(variable: QLVariable, string: QLStringLiteral, expression: QLExpression) {
        self.variable = variable
        self.string = string
        self.expression = expression
    }
    
    override var description: String { return super.description + ".variable = \(variable), .string = \(string) .expression(\n\t\(expression));" }
}