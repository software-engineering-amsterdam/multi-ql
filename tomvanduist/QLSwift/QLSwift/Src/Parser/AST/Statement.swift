//
//  Statement.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation

indirect enum Statement {
    case Question(identifier: Identifier, label: String, expression: Expression)
    case Conditional(condition: Expression, ifBlock: Statement, elseBlock: Statement?)
    case Block(block: [Statement])
}
