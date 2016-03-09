//
//  SymbolTable.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation


typealias Symbol = (type: QLType, question: QLQuestion)

internal class SymbolTable {
    private var symbolTable = [String: Symbol]()
    
    
    func assign(identifier: String, symbol: Symbol) throws {
        if symbolTable[identifier] == nil {
            symbolTable[identifier] = symbol
        }
        else {
            let currentType = retrieveType(identifier)
            
            if currentType! === symbol.type || currentType! === QLUnknownType.self {
                symbolTable[identifier] = symbol
            } else {
                throw MultipleDeclarations(description: "Identifier is multiply declared as different types: \(identifier) as \'\(currentType!.toString())\' and \'\(symbol.type.toString())\'")
            }
        }
    }
    
    func retrieveType(identifier: String) -> QLType? {
        return retrieve(identifier)?.type
    }
    
    func retrieveQuestion(identifier: String) -> QLQuestion? {
        return retrieve(identifier)?.question
    }
    
    func retrieve(identifier: String) -> Symbol? {
        if let o = symbolTable[identifier] {
            return o
        }
        
        return nil
    }
}