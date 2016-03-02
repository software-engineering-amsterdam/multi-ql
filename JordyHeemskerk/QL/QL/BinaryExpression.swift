//
//  Expression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

protocol BinaryExpression: Expression {
    
    var lhs: Expression { get }
    var rhs: Expression { get }
    var op: String { get }
    
}