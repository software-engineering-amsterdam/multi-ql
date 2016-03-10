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
            
            if currentType! === QLUnknownType.self {
                symbolTable[identifier] = symbol
            } else if currentType! === symbol.type  {
                throw OverridingVariable(description: "The variable \'\(identifier)\' overrides an earlier instance.")
            } else {
                throw MultipleDeclarations(description: "The variable \'\(identifier)\' is multiply declared as both \'\(currentType!.toString())\' and \'\(symbol.type.toString())\'")
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