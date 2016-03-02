//
//  QLTypes.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class QLForm: AnyObject {
    let formName: QLString
    let codeBlock: [QLStatement]
    
    init(formName: QLString, codeBlock: [QLStatement]) {
        self.formName = formName
        self.codeBlock = codeBlock
    }
}

class QLStatement: AnyObject {

}

class QLQuestion: QLStatement {
    let question: String
    
    init(question: String) {
        self.question = question
    }
}

class QLExpression: QLStatement {
    
}

// MARK: Data Types
class QLType: QLStatement {
    
}

class QLBool: QLType {
    let boolean: Bool
    
    init(boolean: Bool) {
        self.boolean = boolean
    }
}

class QLString: QLType {
    let string: String
    
    init(string: String) {
        self.string = string
    }
}

class QLInteger: QLType {
    let integer: Int
    
    init(integer: Int) {
        self.integer = integer
    }
}

class QLDate: QLType {
    let date: NSDate
    
    init(date: NSDate) {
        self.date = date
    }
}

class QLDecimal: QLType {
    let decimal: Double
    
    init(decimal: Double) {
        self.decimal = decimal
    }
}

class QLMoney: QLType {
    let money: Float
    
    init(money: Float) {
        self.money = money
    }
}