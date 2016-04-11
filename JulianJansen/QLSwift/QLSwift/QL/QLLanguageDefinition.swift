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
    public static var ql: LanguageDefinition {
        
        var qlDef = empty
        
        qlDef.commentStart = "/*"
        qlDef.commentEnd = "*/"
        qlDef.commentLine = "//"
        
        qlDef.reservedNames = ["boolean", "string", "integer", "if", "else", "true", "false", "form"]
        qlDef.reservedOperators = ["&&", "||", "!", "<", ">", ">=", "<=", "!=", "==", "+", "-", "*", "/"]
        
        return qlDef
    }
}