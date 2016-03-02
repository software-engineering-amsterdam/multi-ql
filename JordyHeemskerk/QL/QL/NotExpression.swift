//
//  MulExpression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct NotExpression: Expression {
    
    let operand: Expression
    let position: Position
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
    
    func inferType(symbolTable: SymbolTable) -> Type {
        return operand.inferType(symbolTable).not()
    }
    
}