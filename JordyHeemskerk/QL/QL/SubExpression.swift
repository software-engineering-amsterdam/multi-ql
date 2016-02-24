//
//  SubtractionExpression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

struct SubExpression: Expression {
    
    let lhs: Expression
    let rhs: Expression
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
    
}