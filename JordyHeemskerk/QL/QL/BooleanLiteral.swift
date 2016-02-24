//
//  BooleanLiteral.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

struct BooleanLiteral: Literal {
    
    let value: Bool
    
    init(booleanString: String) {
        self.value = NSString(string: booleanString).boolValue
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
}