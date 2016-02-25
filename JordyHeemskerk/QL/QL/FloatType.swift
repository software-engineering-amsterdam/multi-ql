//
//  MulExpression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class FloatType: Type {
    
    var description: String { return "Float" }
    
    func compatible(expression: Expression, symbolTable: SymbolTable) -> Bool {
        return expression.inferType(symbolTable).compatibleWithFloat(self)
    }
    
    func compatibleWithInteger(integer: IntegerType) -> Bool {
        return false
    }
    
    func compatibleWithFloat(float: FloatType) -> Bool {
        return true
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
        return expression.inferType(symbolTable).addFloat(self)
    }
    
    func addInteger(integer: IntegerType) -> Type {
        return FloatType()
    }
    
    func addFloat(float: FloatType) -> Type {
        return FloatType()
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
        return expression.inferType(symbolTable).subFloat(self)
    }
    
    func subInteger(integer: IntegerType) -> Type {
        return FloatType()
    }
    
    func subFloat(float: FloatType) -> Type {
        return FloatType()
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
        return expression.inferType(symbolTable).mulFloat(self)
    }
    
    func mulInteger(integer: IntegerType) -> Type {
        return FloatType()
    }
    
    func mulFloat(float: FloatType) -> Type {
        return FloatType()
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
        return expression.inferType(symbolTable).divFloat(self)
    }
    
    func divInteger(integer: IntegerType) -> Type {
        return FloatType()
    }
    
    func divFloat(float: FloatType) -> Type {
        return FloatType()
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