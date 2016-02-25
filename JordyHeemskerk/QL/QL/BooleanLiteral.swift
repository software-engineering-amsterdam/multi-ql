//
//  BooleanLiteral.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct BooleanLiteral: Literal {
    
    let value: Bool
    let position: Position
    
    init(tupleInput: (String, Position)) {
        let (booleanString, currentPosition) = tupleInput
        self.value = NSString(string: booleanString).boolValue
        self.position = currentPosition
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
    
    func inferType(symbolTable: SymbolTable) -> Type {
        return BooleanType()
    }
}