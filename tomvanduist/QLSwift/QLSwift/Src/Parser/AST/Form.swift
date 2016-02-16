//
//  Form.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


protocol FormNode: FormNodeVisitable {
}

class Form: FormNode {
    let identifier: Identifier
    let statement: Statement
    
    init(identifier: Identifier, statement: Statement) {
        self.identifier = identifier
        self.statement = statement
    }
}
