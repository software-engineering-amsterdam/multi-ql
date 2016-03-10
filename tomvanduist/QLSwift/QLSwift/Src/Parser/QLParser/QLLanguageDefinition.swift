//
//  QLLanguageDefinition.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import Foundation
import SwiftParsec

private let emptyOperatorLetterCharacters = "+-*/^!"


extension LanguageDefinition {
    
    public static var ql: LanguageDefinition {
        var qlDef = javaStyle
        
        qlDef.reservedNames     = ["form", "if", "else", "true", "false", "boolean", "integer", "string"]
        qlDef.reservedOperators = [":", "<", ">", "!"]
        
        return qlDef
    }
}