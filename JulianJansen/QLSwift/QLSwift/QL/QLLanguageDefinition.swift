//
//  QLLanguageDefinition.swift
//  ParserTest
//
//  Created by Julian Jansen on 04-02-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation
import SwiftParsec

public extension LanguageDefinition {
    
    // This is a definition for the QL DSL.
    public static var ql: LanguageDefinition {
        
        var qlDef = empty
        
        qlDef.commentStart = "/*"
        qlDef.commentEnd = "*/"
        qlDef.commentLine = "//"
        
        qlDef.reservedNames = ["boolean", "string", "integer", "date", "decimal", "money", "if", "else", "true", "false", "form"]
        qlDef.reservedOperators = ["&&", "||", "!", "<", ">", ">=", "<=", "!=", "==", "+", "-", "*", "/"]
        
        return qlDef
    }
}