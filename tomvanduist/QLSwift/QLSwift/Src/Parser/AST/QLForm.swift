//
//  QLForm.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/02/16.
//
//

import Foundation


class QLForm: QLNode {
    let identifier: QLIdentifier
    let block: QLBlock
    
    init(identifier: QLIdentifier, block: QLBlock) {
        self.identifier = identifier
        self.block = block
    }
}
