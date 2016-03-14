//
//  MulExpression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class FloatType: Type {
    
    override var description: String { return "Float" }
    
    override func compatible(expression: Expression, symbolTable: SymbolTable) -> Bool {
        guard let isCompatible = expression.inferType(symbolTable)?.compatibleWithFloat(self) else {
            return false
        }
        return isCompatible
    }
    
    override func compatibleWithFloat(float: FloatType) -> Bool {
        return true
    }
    
    //MARK: Addition
    override func add(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.addFloat(self)
    }
    
    override func addInteger(integer: IntegerType)-> Type? {
        return FloatType()
    }
    
    override func addFloat(float: FloatType)-> Type? {
        return FloatType()
    }
    
    //MARK: Subtraction
    override func sub(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.subFloat(self)
    }
    
    override func subInteger(integer: IntegerType)-> Type? {
        return FloatType()
    }
    
    override func subFloat(float: FloatType)-> Type? {
        return FloatType()
    }
    
    //MARK: Multiplication
    override func mul(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.mulFloat(self)
    }
    
    override func mulInteger(integer: IntegerType)-> Type? {
        return FloatType()
    }
    
    override func mulFloat(float: FloatType)-> Type? {
        return FloatType()
    }
    
    //MARK: Division
    override func div(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.divFloat(self)
    }
    
    override func divInteger(integer: IntegerType)-> Type? {
        return FloatType()
    }
    
    override func divFloat(float: FloatType)-> Type? {
        return FloatType()
    }
    
    //MARK: Less than
    override func lt(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.ltFloat(self)
    }
    
    override func ltInteger(integer: IntegerType)-> Type? {
        return BooleanType()
    }
    
    override func ltFloat(float: FloatType)-> Type? {
        return BooleanType()
    }
    
    //MARK: Less or equal to
    override func le(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.letFloat(self)
    }
    
    override func letInteger(integer: IntegerType)-> Type? {
        return BooleanType()
    }
    
    override func letFloat(float: FloatType)-> Type? {
        return BooleanType()
    }
    
    //MARK: Greater than
    override func gt(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.gtFloat(self)
    }
    
    override func gtInteger(integer: IntegerType)-> Type? {
        return BooleanType()
    }
    
    override func gtFloat(float: FloatType)-> Type? {
        return BooleanType()
    }
    
    //MARK: Greater or equal to
    override func get(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.getFloat(self)
    }
    
    override func getInteger(integer: IntegerType)-> Type? {
        return BooleanType()
    }
    
    override func getFloat(float: FloatType)-> Type? {
        return BooleanType()
    }
    
    //MARK: Equal to
    override func eq(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.eqFloat(self)
    }
    
    override func eqInteger(integer: IntegerType)-> Type? {
        return BooleanType()
    }
    
    override func eqFloat(float: FloatType)-> Type? {
        return BooleanType()
    }
    
    //MARK: Not equal to
    override func neq(expression: Expression, symbolTable: SymbolTable)-> Type? {
        return expression.inferType(symbolTable)?.neqFloat(self)
    }
    
    override func neqInteger(integer: IntegerType)-> Type? {
        return BooleanType()
    }
    
    override func neqFloat(float: FloatType)-> Type? {
        return BooleanType()
    }
    
}