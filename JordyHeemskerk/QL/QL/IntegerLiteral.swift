//
//  IntegerLiteral.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

struct IntegerLiteral: Literal {
    
    let value: Int
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
}