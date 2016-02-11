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
    let id: Identifier
    let statement: Statement
    
    init(id: Identifier, statement: Statement) {
        self.id = id
        self.statement = statement
    }
}
