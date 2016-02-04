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

class QLStatementList: QLStatement {
    let statements: [QLStatement]
    
    init(statements: [QLStatement]) {
        self.statements = statements
    }
    
    override var description:String {
        var result = super.description + "{\n"
        for stmt in statements {
            result += "\t\(stmt)"
        }
        return result
    }
}