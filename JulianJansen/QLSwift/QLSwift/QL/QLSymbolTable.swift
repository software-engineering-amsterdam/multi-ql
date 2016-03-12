//
//  QLSymbolTable.swift
//  QLSwift
//
//  Created by Julian Jansen on 12-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class QLSymbolTable {
    
    private var symbolTable = Dictionary<String, QLLiteral>()
    
    struct SymbolError: ErrorType {
        let message: String
    }
    
    func getSymbol(identifier: String) throws -> QLLiteral {
        if let symbol = symbolTable[identifier] {
            return symbol
        } else {
            throw SymbolError.init(message: "Symbol not found in symbol table.")
        }
    }
    
    func addSymbol(identifier: String, qlType: QLLiteral) throws {
        if ((symbolTable[identifier] == nil)) {
            symbolTable[identifier] = qlType
        } else {
            throw SymbolError.init(message: "Symbol already exists in symbol table.")
        }
    }
}