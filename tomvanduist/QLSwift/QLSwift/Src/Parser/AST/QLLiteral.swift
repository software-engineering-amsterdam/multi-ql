//
//  QLConst.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

class QLLiteral: NSObject {
    let value: AnyObject
    
    init(value: AnyObject) {
        self.value = value
    }
    
    override var description: String { return super.description + ".value = \(value);" }
}

class QLBooleanLiteral: QLLiteral {
    init(bool: Bool) {
        super.init(value: bool)
    }
    
    override var description: String { return super.description + ".value = \(value as! Bool ? "true" : "false");" }
}

class QLStringLiteral: QLLiteral {
    init(string: String) {
        super.init(value: string)
    }
    
    override var description: String { return super.description + ".value = \"\(value)\";" }
}

class QLIntegerLiteral: QLLiteral {
    init(integer: NSInteger) {
        super.init(value: integer)
    }
}

class QLFloatLiteral: QLLiteral {
    init(float: Double) {
        super.init(value: float)
    }
}