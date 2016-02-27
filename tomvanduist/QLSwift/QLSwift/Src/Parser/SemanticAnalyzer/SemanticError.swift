//
//  SemanticError.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation

enum SemanticError: ErrorType {
    case Collection(errors: [SemanticError])
    case TypeMismatch(description: String)
    case NotDefined(description: String)
    case Generic(description: String)
    case System(error: ErrorType)
    case None
}

extension SemanticError {
    mutating func collect(error: ErrorType) {
        if let error = error as? SemanticError {
            switch self {
            case .None: self = error
            default: self = SemanticError.Collection(errors: self.errors + error.errors)
            }
        } else {
            self = SemanticError.System(error: error)
        }
    }
    
    var errors: [SemanticError] {
        switch self {
        case .Collection(let errors): return errors
        case .None: return []
        default: return [self]
        }
    }
}

enum SemanticWarning: ErrorType {
    case MultipleDeclarations(description: String)
}


