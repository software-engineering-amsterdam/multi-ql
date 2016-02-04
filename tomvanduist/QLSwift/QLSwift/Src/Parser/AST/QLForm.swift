//
//  QLForm.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation

class QLForm: NSObject {
    
    let statement: QLStatement;
    
    init(statement: QLStatement) {
        self.statement = statement;
    }
}