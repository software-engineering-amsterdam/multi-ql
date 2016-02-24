//
//  Form.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

struct Form: ASTNode {
    
    let identifier: String
    let block: Block
    
    init(tupleInput: (String, Block)) {
        let (identifier, block) = tupleInput
        self.identifier = identifier
        self.block = block
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
    
}