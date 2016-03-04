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

class CyclomaticDependencyError: SemanticError {
}

class SystemError: SemanticError {
    init(error: ErrorType) {
        super.init(description: "\(error)")
    }
}

class SemanticErrorCollection: NSObject, ErrorType {
    private var _errors: [SemanticError]
    var errors: [SemanticError] {
        get {
            return _errors
        }
    }
    
    init(errors: [SemanticError]) {
        self._errors = errors
    }
    
    func collectError(error: SemanticError) {
        self.collectErrors([error])
    }
    
    func collectErrors(errors: [SemanticError]) {
        _errors += errors
    }
    
    override var description: String {
        var _description: String = ""
        
        errors.forEach { error in
            _description += "\(error)\n"
        }
        
        return _description
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
