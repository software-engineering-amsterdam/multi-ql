//
//  SubtractionExpression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct SubExpression: BinaryExpression {
    
    let lhs: Expression
    let rhs: Expression
    let position: Position
    let op = "-"
    
    func accept<T: ExpressionVisitor>(visitor: T) -> T.ExpressionReturnType {
        return visitor.visit(self)
    }
    
    func inferType(symbolTable: SymbolTable) -> Type? {
        return lhs.inferType(symbolTable)?.sub(rhs, symbolTable: symbolTable)
    }
    
}