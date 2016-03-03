//
//  QLNodes.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol ASTNode {}
protocol ASTTerminal: ASTNode {}
protocol ASTNonTerminal: ASTNode {}

protocol QLStatement: ASTNonTerminal {}
protocol QLExpression: QLStatement {}

// MARK: Non-terminals.

class QLForm: ASTNonTerminal {
    let formName: String
    let codeBlock: [QLStatement]
    
    init(formName: String, codeBlock: [QLStatement]) {
        self.formName = formName
        self.codeBlock = codeBlock
    }
}

class QLQuestion: QLStatement {
    let name: String
    let variable: String
    let type: String
    
    init(name: String, variable: String, type: String) {
        self.name = name
        self.variable = variable
        self.type = type
    }
}

// MARK: Terminals.

class QLBool: ASTTerminal {
    let boolean: Bool
    
    init(boolean: Bool) {
        self.boolean = boolean
    }
}

class QLString: ASTTerminal {
    let string: String
    
    init(string: String) {
        self.string = string
    }
}

class QLInteger: ASTTerminal {
    let integer: Int
    
    init(integer: Int) {
        self.integer = integer
    }
}

class QLDate: ASTTerminal {
    let date: NSDate
    
    init(date: NSDate) {
        self.date = date
    }
}

class QLDecimal: ASTTerminal {
    let decimal: Double
    
    init(decimal: Double) {
        self.decimal = decimal
    }
}

class QLMoney: ASTTerminal {
    let money: Float
    
    init(money: Float) {
        self.money = money
    }
}