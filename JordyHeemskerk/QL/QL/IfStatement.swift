//
//  IfStatement.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct IfStatement: Statement {
    
    let conditionClause: Expression
    let block: Block
    let elseClause: ElseIfStatement?
    let position: Position
    
    init(tupleInput: (((Expression, Block), [ElseIfStatement]), Position)) {
        let (((conditionClause, block), elseClause), currentPosition) = tupleInput
        self.conditionClause = conditionClause
        self.block = block
        self.elseClause = elseClause.first
        self.position = currentPosition
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
}