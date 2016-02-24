//
//  ElseIfStatement.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

struct ElseIfStatement: Statement {
    
    let conditionClause: ConditionClause?
    let block: Block
    let elseClause: Statement? // Because structs dont allow recursive values
    
    init(block: Block) {
        self.block = block
        self.conditionClause = nil
        self.elseClause = nil
    }
    
    init(ifStatement: IfStatement) {
        self.conditionClause = ifStatement.conditionClause
        self.block = ifStatement.block
        self.elseClause = ifStatement.elseClause
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
}