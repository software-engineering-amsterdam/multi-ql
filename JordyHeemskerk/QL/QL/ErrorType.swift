//
//  MulExpression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class ErrorType: Type {
    
    var description: String { return "ErrorType" }
    
    func compatible(expression: Expression, symbolTable: SymbolTable) -> Bool {
        return false
    }
    
    func compatibleWithInteger(integer: IntegerType) -> Bool {
        return false
    }
    
    func compatibleWithFloat(float: FloatType) -> Bool {
        return false
    }
    
    func compatibleWithBoolean(boolean: BooleanType) -> Bool {
        return false
    }
    
    func compatibleWithString(string: StringType) -> Bool{
        return false
    }
    
    func compatibleWithError(error: ErrorType) -> Bool {
        return false
    }
    
    // Addition type checking
    func add(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func addInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func addFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func addBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func addString(bool: StringType) -> Type {
        return ErrorType()
    }
    
    func addError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Subtraction type checking
    func sub(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func subInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func subFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func subBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func subString(bool: StringType) -> Type {
        return ErrorType()
    }
    
    func subError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Multiplication type checking
    func mul(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func mulInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func mulFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func mulBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func mulString(bool: StringType) -> Type {
        return ErrorType()
    }
    
    func mulError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Multiplication type checking
    func div(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func divInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func divFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func divBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func divString(bool: StringType) -> Type {
        return ErrorType()
    }
    
    func divError(error: ErrorType) -> Type {
        return ErrorType()
    }

}