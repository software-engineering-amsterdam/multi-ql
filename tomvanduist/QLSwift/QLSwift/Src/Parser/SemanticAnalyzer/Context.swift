//
//  Context.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation


typealias Object = (type: ExpressionType, expression: Expression?)

class Context {
    static let sharedInstance = Context()
    
    let parent: Context?
    private var context = [String: Object]()
    
    init(parent: Context? = nil) {
        self.parent = parent
    }
    
    func assign(identifier: Identifier, object: Object) throws {
        if context[identifier.id] == nil {
            context[identifier.id] = object
        }
        else {
            throw SemanticWarning.MultipleDeclarations(description: "Identifier is already declared: \(identifier)")
        }
    }
    
    func retrieve(identifier: Identifier) -> Object? {
        if let o = context[identifier.id] {
            return o
        }
        return parent?.retrieve(identifier)
    }
}