//
//  QLVariable.swift
//  QLSwift
//
//  Created by Tom van Duist on 04/02/16.
//
//

import Foundation

class QLVariable: NSObject {
    let identifier: String
    
    init(identifier: String) {
        self.identifier = identifier
    }
    
    override var description: String { return super.description + ".identifier = \(identifier);" }
}