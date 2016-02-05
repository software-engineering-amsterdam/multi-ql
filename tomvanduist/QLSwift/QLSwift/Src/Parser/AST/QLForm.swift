//
//  QLForm.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation

class QLForm: NSObject {
    let variable: QLVariable
    let statement: QLStatement
    
    init(variable: QLVariable, statement: QLStatement) {
        self.variable = variable
        self.statement = statement
    }
    
    override var description: String { return super.description + ".variable = \(variable), .statement(\n\t\(statement));" }
}