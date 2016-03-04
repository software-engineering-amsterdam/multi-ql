//
//  SemanticError.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation


// MARK: - Semantic Error

class SemanticError: NSObject, ErrorType {
    private let _description: String
    
    init(description: String) {
        self._description = description
    }
    
    override var description: String {
        return _description
    }
}

class TypeMismatchError: SemanticError {
}

class UndefinedVariableError: SemanticError {
}

class SystemError: SemanticError {
    init(error: ErrorType) {
        super.init(description: "\(error)")
    }
}

class SemanticErrorCollection: NSObject, ErrorType {
    let errors: [SemanticError]
    
    init(errors: [SemanticError]) {
        self.errors = errors
    }
}


// MARK: - Semantic Warning

class SemanticWarning: NSObject, ErrorType {
    private let _description: String
    
    init(description: String) {
        self._description = description
    }
    
    override var description: String {
        return _description
    }
}

class MultipleDeclarations: SemanticWarning {
}
