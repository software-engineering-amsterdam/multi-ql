//
//  Type.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class Type: CustomStringConvertible {
    
    var description: String { return "SuperType" }
    
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
    
    func addString(string: StringType) -> Type {
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
    
    func subString(string: StringType) -> Type {
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
    
    func mulString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func mulError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Division type checking
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
    
    func divString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func divError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Less than type checking
    func lt(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func ltInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func ltFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func ltBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func ltString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func ltError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Less or equal than type checking
    func le(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func letInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func letFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func letBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func letString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func letError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Greater than type checking
    func gt(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func gtInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func gtFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func gtBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func gtString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func gtError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Greater or equal than type checking
    func get(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func getInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func getFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func getBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func getString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func getError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Equal to type checking
    func eq(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func eqInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func eqFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func eqBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func eqString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func eqError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Not equal to type checking
    func neq(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func neqInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func neqFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func neqBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func neqString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func neqError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // And type checking
    func and(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func andInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func andFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func andBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func andString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func andError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Or type checking
    func or(expression: Expression, symbolTable: SymbolTable) -> Type {
        return ErrorType()
    }
    
    func orInteger(integer: IntegerType) -> Type {
        return ErrorType()
    }
    
    func orFloat(float: FloatType) -> Type {
        return ErrorType()
    }
    
    func orBool(bool: BooleanType) -> Type {
        return ErrorType()
    }
    
    func orString(string: StringType) -> Type {
        return ErrorType()
    }
    
    func orError(error: ErrorType) -> Type {
        return ErrorType()
    }
    
    // Not type checking
    func not() -> Type {
        return ErrorType()
    }
    
}