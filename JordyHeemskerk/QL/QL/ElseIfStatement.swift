//
//  ElseIfStatement.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct ElseIfStatement: Statement {
    
    let conditionClause: Expression?
    let block: Block
    let elseClause: Statement? // Because structs dont allow recursive values
    let position: Position
    
    init(tupleInput: (Block, Position)) {
        let (block, currentPosition) = tupleInput
        self.block = block
        self.conditionClause = nil
        self.elseClause = nil
        self.position = currentPosition
    }
    
    init(tupleInput: (IfStatement, Position)) {
        let (ifStatement, currentPosition) = tupleInput
        self.conditionClause = ifStatement.conditionClause
        self.block = ifStatement.block
        self.elseClause = ifStatement.elseClause
        self.position = currentPosition
    }
    
    func accept<T: StatementVisitor>(visitor: T) -> T.StatementReturnType {
        return visitor.visit(self)
    }
}