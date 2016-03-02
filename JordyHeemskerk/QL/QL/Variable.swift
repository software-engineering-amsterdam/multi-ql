//
//  Variable.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct Variable: Literal {
    
    let identifier: String
    let position: Position
    
    init(tupleInput: (String, Position)) {
        let (identifier, currentPosition) = tupleInput
        self.identifier = identifier
        self.position = currentPosition
    }
    
    func accept<T: ExpressionVisitor>(visitor: T) -> T.ExpressionReturnType {
        return visitor.visit(self)
    }
    
    func inferType(symbolTable: SymbolTable) -> Type? {
        return symbolTable.getVariableType(identifier)
    }
}