//
//  QLSymbolTable.swift
//  QLSwift
//
//  Created by Julian Jansen on 12-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class QLSymbolTable {
    
    private var symbolTable = Dictionary<String, QLLiteral>()
    
    struct SymbolError: ErrorType {
        let message: String
    }
    
    func getSymbolTable() -> Dictionary<String, QLLiteral> {
        return symbolTable
    }
    
    func getSymbol(identifier: String) throws -> QLLiteral {
        if let symbol = symbolTable[identifier] {
            return symbol
        } else {
            throw SymbolError.init(message: "Symbol not found in symbol table.")
        }
    }
    
    func addSymbol(identifier: String, qlType: QLLiteral) throws {
        if ((symbolTable[identifier] == nil)) {
            symbolTable[identifier] = qlType
        } else {
            throw SymbolError.init(message: "Symbol already exists in symbol table.")
        }
    }
}

class SymbolsVisitor: Visitor {
    
    private var symbolTable = QLSymbolTable()
    
    func getSymbolTable() -> QLSymbolTable {
        return symbolTable
    }
    
    func visit(qlform: QLForm) {
        
        for statement in qlform.codeBlock {
            statement.accept(self)
        }
        
    }
    
    func visit(qlquestion: QLQuestion) {
        do {
            try symbolTable.addSymbol(qlquestion.variable.identifier, qlType: qlquestion.type)
        } catch {
            print(error)
        }
    }
    
    func visit(qlifstatement: QLIfStatement) {
        qlifstatement.condition.accept(self)
        
        for statement in qlifstatement.codeBlock {
            statement.accept(self)
        }
    }
    
    func visit(qlvariable: QLVariable) {
    
    }
    
    // MARK: Expressions.
    func visit(qlunaryexpression: QLUnaryExpression) {
        qlunaryexpression.literal.accept(self)
    }
    
    func visit(qlbinaryexpression: QLBinaryExpression) {
        qlbinaryexpression.lhs.accept(self)
        qlbinaryexpression.rhs.accept(self)
    }
    
    func visit(qlnotexpression: QLNotExpression) {
        qlnotexpression.accept(self)
    }
    
    func visit(qlgreaterthanexpression: QLGreaterThanExpression) {
        qlgreaterthanexpression.lhs.accept(self)
        qlgreaterthanexpression.rhs.accept(self)
    }
    
    func visit(qlsmallerthanexpression: QLSmallerThanExpression) {
        qlsmallerthanexpression.lhs.accept(self)
        qlsmallerthanexpression.rhs.accept(self)
    }
    
    func visit(qlgreaterorisexpression: QLGreaterOrIsExpression) {
        qlgreaterorisexpression.lhs.accept(self)
        qlgreaterorisexpression.rhs.accept(self)
    }
    
    func visit(qlsmallerorisexpression: QLSmallerOrISExpression) {
        qlsmallerorisexpression.lhs.accept(self)
        qlsmallerorisexpression.rhs.accept(self)
    }
    
    func visit(qlisnotexpression: QLIsNotExpression) {
        qlisnotexpression.lhs.accept(self)
        qlisnotexpression.rhs.accept(self)
    }
    
    func visit(qlisexpression: QLIsExpression) {
        qlisexpression.lhs.accept(self)
        qlisexpression.rhs.accept(self)
    }
    
    func visit(qlmultiplyexpression: QLMultiplyExpression) {
        qlmultiplyexpression.lhs.accept(self)
        qlmultiplyexpression.rhs.accept(self)
    }
    
    func visit(qldivideexpression: QLDivideExpression) {
        qldivideexpression.lhs.accept(self)
        qldivideexpression.rhs.accept(self)
    }
    
    func visit(qladdexpression: QLAddExpression) {
        qladdexpression.lhs.accept(self)
        qladdexpression.rhs.accept(self)
    }
    
    func visit(qlsubtractexpression: QLSubtractExpression) {
        qlsubtractexpression.lhs.accept(self)
        qlsubtractexpression.rhs.accept(self)
    }
    
    func visit(qlandexpression: QLAndExpression) {
        qlandexpression.lhs.accept(self)
        qlandexpression.rhs.accept(self)
    }
    
    func visit(qlorexpression: QLOrExpression) {
        qlorexpression.lhs.accept(self)
        qlorexpression.rhs.accept(self)
    }
    
    // MARK: Literals.
    func visit(qlunknownliteral: QLUnknownLiteral) { }
    
    func visit(qlbool: QLBool) { }
    
    func visit(qlstring: QLString) { }
    
    func visit(qlinteger: QLInteger) { }
    
    func visit(qldate: QLDate) { }
    
    func visit(qldecimal: QLDecimal) { }
    
    func visit(qlmoney: QLMoney) { }
    
}