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


public extension LanguageDefinition {
    
    
    public static var ql: LanguageDefinition {
        var qlDef = javaStyle
        
        qlDef.reservedNames     = ["money"]
        qlDef.reservedOperators = ["if", "money"]
        
        return qlDef
    }
}