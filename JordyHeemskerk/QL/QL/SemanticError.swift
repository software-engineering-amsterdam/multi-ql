//
//  SemanticErrors.swift
//  QL
//
//  Created by Jordy Heemskerk on 25/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

enum SemanticError: SemanticLoggable {
    
    case VariableAlreadyDefined(identifier: String, position: Position)
    case ComputedTypeMismatch(identifier: String, expectedType: Type, inferedType: Type, position: Position)
    case ConditionTypeMismatch(inferedType: Type, position: Position)
    
    var description: String {
        return "Error: \(self.errorDescription)"
    }
    
    private var errorDescription: String {
        switch self {
        case let .VariableAlreadyDefined(identifier, position):
            return "Variable \(identifier) already defined. [\(position.line):\(position.character)]"
        case let .ComputedTypeMismatch(identifier, expectedType, inferedType, position):
            return "Computed type of variable '\(identifier)' does not match declaration, expected \(expectedType) but got \(inferedType). [\(position.line):\(position.character)]"
        case  let .ConditionTypeMismatch(inferedType, position):
            let expecedType = BooleanType()
            return "Condition type is not of type \(expecedType), expected \(expecedType) but got \(inferedType). [\(position.line):\(position.character)]"
        }
    }

}