//
//  QLSymbolTable.swift
//  QLSwift
//
//  Created by Julian Jansen on 12-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class SymbolTable {
    
    private var symbolTable = Dictionary<String, QLType>()
    
    func getSymbolTable() -> Dictionary<String, QLType> {
        return symbolTable
    }
    
    func getSymbol(identifier: String) throws -> QLType {
        if let symbol = symbolTable[identifier] {
            return symbol
        } else {
            throw SymbolError(message: "Symbol \(identifier) not found in symbol table while getting symbol.")
        }
    }
    
    func getSymbolType(variable: QLVariable) throws -> QLType {
        if let symbol = symbolTable[variable.name] {
            return symbol
        } else {
            throw SymbolError(message: "Symbol \(variable) not found in symbol table while getting type.")
        }
    }
    
    func addSymbol(identifier: String, qlType: QLType) throws {
        if (symbolTable[identifier] == nil) {
            symbolTable[identifier] = qlType
        } else {
            throw SymbolError.init(message: "Symbol \(identifier) already exists in symbol table.")
        }
    }
}