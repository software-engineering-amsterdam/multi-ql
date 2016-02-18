//
//  QLForm.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation


class QLForm {
    let variable: QLVariable
    let block: QLBlockStatement
    
    init(variable: QLVariable, block: QLBlockStatement) {
        self.variable = variable
        self.block = block
    }
}
