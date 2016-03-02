//
//  FormVisitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 02/03/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

protocol BlockVisitor {
    typealias BlockReturnType
    func visit(block: Block) -> BlockReturnType
}