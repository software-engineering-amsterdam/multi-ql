//
//  Addition.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct AddExpression: Expression {
    
    let lhs: Expression
    let rhs: Expression
    let position: Position
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
    
    func inferType(symbolTable: SymbolTable) -> Type {
        return lhs.inferType(symbolTable).add(rhs, symbolTable: symbolTable)
    }
    
}