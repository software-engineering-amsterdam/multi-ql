//
//  Context.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation


typealias Object = (type: FormNodeType, expression: Expression?)

class Context {
    static let sharedInstance = Context()
    
    private var context = [String: Object]()
    
    func assign(identifier: Identifier, object: Object) throws {
        if context[identifier.id] == nil {
            context[identifier.id] = object
        }
        else {
            throw SemanticError.MultipleDeclarations(description: "Identifier is already declared: \(identifier)")
        }
    }
    
    func retrieve(identifier: Identifier) -> Object? {
        return context[identifier.id]
    }
}