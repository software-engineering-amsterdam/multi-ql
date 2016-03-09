//
//  SymbolTable.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation


typealias Object = (type: QLType, question: QLQuestion)

internal class SymbolTable {
    private var symbolTable = [String: Object]()
    
    
    func assign(identifier: String, object: Object) throws {
        if symbolTable[identifier] == nil {
            symbolTable[identifier] = object
        }
        else {
            let currentType = retrieveType(identifier)
            
            if currentType! === object.type || currentType! === QLUnknownType.self {
                symbolTable[identifier] = object
            } else {
                throw MultipleDeclarations(description: "Identifier is multiply declared as different types: \(identifier) as \'\(currentType!.toString())\' and \'\(object.type.toString())\'")
            }
        }
    }
    
    func retrieveType(identifier: String) -> QLType? {
        return retrieve(identifier)?.type
    }
    
    func retrieveQuestion(identifier: String) -> QLQuestion? {
        return retrieve(identifier)?.question
    }
    
    func retrieve(identifier: String) -> Object? {
        if let o = symbolTable[identifier] {
            return o
        }
        
        return nil
    }
}