//
//  Expression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

protocol Expression: ASTNode {
    
    func accept<T: ExpressionVisitor>(visitor: T) -> T.ExpressionReturnType

    func inferType(symbolTable: SymbolTable) -> Type?
    
}