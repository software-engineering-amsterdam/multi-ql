//
//  File.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

struct SymbolTable {
    
    internal var symbolTable = [String:Type]()
    
    mutating func defineVariable(identifier: String, type: Type) {
        symbolTable[identifier] = type
    }
    
    func isVariableDefined(identifier: String) -> Bool {
        return symbolTable[identifier] != nil
    }
    
    func getVariableType(identifier: String) -> Type {
        return symbolTable[identifier] ?? ErrorType()
    }
    
}