//
//  IntegerLiteral.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct IntegerLiteral: Literal {
    
    let value: Int
    let position: Position
    
    init(tupleInput: (Int, Position)) {
        let (value, currentPosition) = tupleInput
        self.value = value
        self.position = currentPosition
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
    
    func inferType(symbolTable: SymbolTable) -> Type {
        return IntegerType()
    }
    
}