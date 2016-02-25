//
//  Block.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct Block: ASTNode {
    
    let statements: [Statement]
    let position: Position
    
    init(tupleInput: ([Statement], Position)) {
        let (statements, currentPosition) = tupleInput
        self.statements = statements
        self.position = currentPosition
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
    
}