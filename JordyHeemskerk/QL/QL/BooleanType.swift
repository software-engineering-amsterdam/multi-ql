//
//  MulExpression.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class BooleanType: Type {
    
    override var description: String { return "Boolean" }
    
    override func compatible(expression: Expression, symbolTable: SymbolTable) -> Bool {
        return expression.inferType(symbolTable).compatibleWithBoolean(self)
    }
    
    override func compatibleWithBoolean(boolean: BooleanType) -> Bool {
        return true
    }
    
    //MARK: Less than
    override func lt(expression: Expression, symbolTable: SymbolTable) -> Type {
        return expression.inferType(symbolTable).ltBool(self)
    }
    
    override func ltBool(bool: BooleanType) -> Type {
        return BooleanType()
    }
    
    //MARK: Less or equal to
    override func le(expression: Expression, symbolTable: SymbolTable) -> Type {
        return expression.inferType(symbolTable).letBool(self)
    }
    
    override func letBool(bool: BooleanType) -> Type {
        return BooleanType()
    }
    
    //MARK: Greater than
    override func gt(expression: Expression, symbolTable: SymbolTable) -> Type {
        return expression.inferType(symbolTable).gtBool(self)
    }
    
    override func gtBool(bool: BooleanType) -> Type {
        return BooleanType()
    }
    
    //MARK: Greater or equal to
    override func get(expression: Expression, symbolTable: SymbolTable) -> Type {
        return expression.inferType(symbolTable).getBool(self)
    }
    
    override func getBool(bool: BooleanType) -> Type {
        return BooleanType()
    }
    
    //MARK: Equal to
    override func eq(expression: Expression, symbolTable: SymbolTable) -> Type {
        return expression.inferType(symbolTable).eqBool(self)
    }
    
    override func eqBool(bool: BooleanType) -> Type {
        return BooleanType()
    }
    
    //MARK: Not equal to
    override func neq(expression: Expression, symbolTable: SymbolTable) -> Type {
        return expression.inferType(symbolTable).neqBool(self)
    }
    
    override func neqBool(bool: BooleanType) -> Type {
        return BooleanType()
    }
    
    //MARK: And
    override func and(expression: Expression, symbolTable: SymbolTable) -> Type {
        return expression.inferType(symbolTable).andBool(self)
    }
    
    override func andBool(bool: BooleanType) -> Type {
        return BooleanType()
    }
    
    //MARK: Or
    override func or(expression: Expression, symbolTable: SymbolTable) -> Type {
        return expression.inferType(symbolTable).orBool(self)
    }
    
    override func orBool(bool: BooleanType) -> Type {
        return BooleanType()
    }
    
    //MARK: Not
    override func not() -> Type {
        return BooleanType()
    }
    
}