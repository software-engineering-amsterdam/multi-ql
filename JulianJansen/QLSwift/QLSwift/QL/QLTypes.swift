//
//  QLTypes.swift
//  ParserTest
//
//  Created by Julian Jansen on 24-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation


class QLType: NSObject {
    
}

class QLForm: AnyObject {
    let formName: QLString
    let codeBlocks: QLCodeBlock
    
    init(formName: QLString, codeBlocks: QLCodeBlock) {
        self.formName = formName
        self.codeBlocks = codeBlocks
    }
}

class QLCodeBlock: AnyObject {
    let codeBlock: [QLString]
    
    init(codeBlock: [QLString]) {
        self.codeBlock = codeBlock
    }
}

// MARK: Data Types

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