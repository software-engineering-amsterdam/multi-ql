//
//  Type.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

protocol Type: CustomStringConvertible {
    
    func compatible(expression: Expression, symbolTable: SymbolTable) -> Bool
    func compatibleWithInteger(integer: IntegerType) -> Bool
    func compatibleWithFloat(float: FloatType) -> Bool
    func compatibleWithBoolean(boolean: BooleanType) -> Bool
    func compatibleWithString(string: StringType) -> Bool
    func compatibleWithError(error: ErrorType) -> Bool
    
    func add(expression: Expression, symbolTable: SymbolTable) -> Type
    func addInteger(integer: IntegerType) -> Type
    func addFloat(float: FloatType) -> Type
    func addBool(bool: BooleanType) -> Type
    func addString(bool: StringType) -> Type
    func addError(error: ErrorType) -> Type
    
    func sub(expression: Expression, symbolTable: SymbolTable) -> Type
    func subInteger(integer: IntegerType) -> Type
    func subFloat(float: FloatType) -> Type
    func subBool(bool: BooleanType) -> Type
    func subString(bool: StringType) -> Type
    func subError(error: ErrorType) -> Type
    
    func mul(expression: Expression, symbolTable: SymbolTable) -> Type
    func mulInteger(integer: IntegerType) -> Type
    func mulFloat(float: FloatType) -> Type
    func mulBool(bool: BooleanType) -> Type
    func mulString(bool: StringType) -> Type
    func mulError(error: ErrorType) -> Type
    
    func div(expression: Expression, symbolTable: SymbolTable) -> Type
    func divInteger(integer: IntegerType) -> Type
    func divFloat(float: FloatType) -> Type
    func divBool(bool: BooleanType) -> Type
    func divString(bool: StringType) -> Type
    func divError(error: ErrorType) -> Type
    
}