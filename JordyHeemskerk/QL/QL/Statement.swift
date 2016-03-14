//
//  Statement.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

protocol Statement: ASTNode {
    
    func accept<T: StatementVisitor>(visitor: T) -> T.StatementReturnType
    
}