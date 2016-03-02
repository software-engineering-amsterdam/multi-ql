//
//  MulExpression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class StringType: Type {
    
    override var description: String { return "String" }
    
    //MARK: Compatible with
    override func compatible(expression: Expression, symbolTable: SymbolTable) -> Bool {
        guard let isCompatible = expression.inferType(symbolTable)?.compatibleWithString(self) else {
            return false
        }
        return isCompatible
    }
    
    override func compatibleWithString(string: StringType) -> Bool{
        return true
    }
    
    //MARK:  Equal to
    override func eq(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.eqString(self)
    }
    
    override func eqString(string: StringType)-> Type? {
        return BooleanType()
    }
    
    //MARK: Not equal
    override func neq(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.neqString(self)
    }
    
    override func neqString(string: StringType)-> Type? {
        return BooleanType()
    }

}