//
//  FloatLiteral.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct FloatLiteral: Literal {
    
    let value: Double
    let position: Position
    
    init(tupleInput: (Double, Position)) {
        let (value, currentPosition) = tupleInput
        self.value = value
        self.position = currentPosition
    }
    
    func accept<T: ExpressionVisitor>(visitor: T) -> T.ExpressionReturnType {
        return visitor.visit(self)
    }
    
    func inferType(symbolTable: SymbolTable) -> Type? {
        return FloatType()
    }
    
}