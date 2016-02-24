//
//  Form.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


protocol ASTNode: ASTNodeVisitable {
}

class Form: ASTNode {
    let identifier: Identifier
    let block: Block
    
    init(identifier: Identifier, block: Block) {
        self.identifier = identifier
        self.block = block
    }
}
