//
//  QLIdentifier.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/03/16.
//
//

import Foundation

class QLIdentifier: QLNode {
    let id: String
    
    init(id: String) {
        self.id = id
    }
    
    func toString() -> String {
        return id
    }
}