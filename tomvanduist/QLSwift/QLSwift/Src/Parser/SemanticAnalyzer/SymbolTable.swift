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
    let parent: SymbolTable?
    private var symbolTable = [String: Object]()
    
    init(parent: SymbolTable? = nil) {
        self.parent = parent
    }
    
    func assign(identifier: String, object: Object) throws {
        if symbolTable[identifier] == nil {
            symbolTable[identifier] = object
        }
        else {
            throw MultipleDeclarations(description: "Identifier is already declared: \(identifier)")
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
        return parent?.retrieve(identifier)
    }
}