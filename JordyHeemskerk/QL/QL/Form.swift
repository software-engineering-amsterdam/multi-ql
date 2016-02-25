//
//  Form.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct Form: ASTNode {
    
    let identifier: String
    let block: Block
    let position: Position
    
    init(tupleInput: ((String, Block), Position)) {
        let ((identifier, block), currentPosition) = tupleInput
        self.identifier = identifier
        self.block = block
        self.position = currentPosition
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
    
}