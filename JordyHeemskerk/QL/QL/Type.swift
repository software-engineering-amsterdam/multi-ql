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
    
    // Addition Type? checking
    func add(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func addInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func addFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func addBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func addString(string: StringType) -> Type? {
        return nil
    }
    
    func addError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Subtraction Type? checking
    func sub(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func subInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func subFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func subBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func subString(string: StringType) -> Type? {
        return nil
    }
    
    func subError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Multiplication Type? checking
    func mul(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func mulInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func mulFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func mulBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func mulString(string: StringType) -> Type? {
        return nil
    }
    
    func mulError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Division Type? checking
    func div(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func divInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func divFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func divBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func divString(string: StringType) -> Type? {
        return nil
    }
    
    func divError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Less than Type? checking
    func lt(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func ltInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func ltFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func ltBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func ltString(string: StringType) -> Type? {
        return nil
    }
    
    func ltError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Less or equal than Type? checking
    func le(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func letInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func letFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func letBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func letString(string: StringType) -> Type? {
        return nil
    }
    
    func letError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Greater than Type? checking
    func gt(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func gtInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func gtFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func gtBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func gtString(string: StringType) -> Type? {
        return nil
    }
    
    func gtError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Greater or equal than Type? checking
    func get(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func getInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func getFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func getBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func getString(string: StringType) -> Type? {
        return nil
    }
    
    func getError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Equal to Type? checking
    func eq(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func eqInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func eqFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func eqBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func eqString(string: StringType) -> Type? {
        return nil
    }
    
    func eqError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Not equal to Type? checking
    func neq(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func neqInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func neqFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func neqBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func neqString(string: StringType) -> Type? {
        return nil
    }
    
    func neqError(error: ErrorType) -> Type? {
        return nil
    }
    
    // And Type? checking
    func and(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func andInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func andFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func andBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func andString(string: StringType) -> Type? {
        return nil
    }
    
    func andError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Or Type? checking
    func or(expression: Expression, symbolTable: SymbolTable) -> Type? {
        return nil
    }
    
    func orInteger(integer: IntegerType) -> Type? {
        return nil
    }
    
    func orFloat(float: FloatType) -> Type? {
        return nil
    }
    
    func orBool(bool: BooleanType) -> Type? {
        return nil
    }
    
    func orString(string: StringType) -> Type? {
        return nil
    }
    
    func orError(error: ErrorType) -> Type? {
        return nil
    }
    
    // Not Type? checking
    func not() -> Type? {
        return nil
    }
    
}