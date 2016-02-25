//
//  SemanticErrors.swift
//  QL
//
//  Created by Jordy Heemskerk on 25/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

enum SemanticError: SemanticLoggable {
    
    case VariableAlreadyDefined(identifier: String)
    case ComputedTypeMismatch(identifier: String, expectedType: Type, inferedType: Type)
    case ConditionTypeMismatch(inferedType: Type)
    
    var description: String {
        let prefix = "Error:"
        switch self {
        case let .VariableAlreadyDefined(identifier):
            return "\(prefix) Variable \(identifier) already defined."
        case let .ComputedTypeMismatch(identifier, expectedType, inferedType):
            return "\(prefix) Computed type of variable '\(identifier)' does not match declaration, expected \(expectedType) but got \(inferedType)"
        case  let .ConditionTypeMismatch(inferedType):
            let expecedType = BooleanType()
            return "\(prefix) Condition type is not of type \(expecedType), expected \(expecedType) but got \(inferedType)"
        }
    }

}