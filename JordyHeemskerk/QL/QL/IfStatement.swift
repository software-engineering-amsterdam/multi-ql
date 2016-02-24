//
//  IfStatement.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

struct IfStatement: Statement {
    
    let conditionClause: Expression
    let block: Block
    let elseClause: ElseIfStatement?
    
    init(tupleInput: ((Expression, Block), [ElseIfStatement])) {
        let ((conditionClause, block), elseClause) = tupleInput
        self.conditionClause = conditionClause
        self.block = block
        self.elseClause = elseClause.first
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
}