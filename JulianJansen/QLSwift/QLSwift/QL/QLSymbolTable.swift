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
        print("If-statement")
        
        qlifstatement.condition.accept(self)
        
        for statement in qlifstatement.codeBlock {
            statement.accept(self)
        }
    }
    
    func visit(qlvariable: QLVariable) {
        print("-> Variable")
    }
    
    // MARK: Expressions.
    func visit(qlunaryexpression: QLUnaryExpression) {
        print("-> Unary expression")
        qlunaryexpression.expression.accept(self)
    }
    
    func visit(qlnotexpression: QLNotExpression) {
        print("-> Not expression")
        qlnotexpression.accept(self)
    }
    
    
    func visit(qlgreaterthanexpression: QLGreaterThanExpression) {
        print("Greater than")
        qlgreaterthanexpression.lhs.accept(self)
        qlgreaterthanexpression.rhs.accept(self)
    }
    
    func visit(qlsmallerthanexpression: QLSmallerThanExpression) {
        print("Smaller than")
        qlsmallerthanexpression.lhs.accept(self)
        qlsmallerthanexpression.rhs.accept(self)
    }
    
    func visit(qlgreaterorisexpression: QLGreaterOrIsExpression) {
        print("Greater or is")
        qlgreaterorisexpression.lhs.accept(self)
        qlgreaterorisexpression.rhs.accept(self)
    }
    
    func visit(qlsmallerorisexpression: QLSmallerOrISExpression) {
        print("Smaller or is")
        qlsmallerorisexpression.lhs.accept(self)
        qlsmallerorisexpression.rhs.accept(self)
    }
    
    func visit(qlisnotexpression: QLIsNotExpression) {
        print("Is not")
        qlisnotexpression.lhs.accept(self)
        qlisnotexpression.rhs.accept(self)
    }
    
    func visit(qlisexpression: QLIsExpression) {
        print("Is")
        qlisexpression.lhs.accept(self)
        qlisexpression.rhs.accept(self)
    }
    
    func visit(qlmultiplyexpression: QLMultiplyExpression) {
        print("Multiply")
        qlmultiplyexpression.lhs.accept(self)
        qlmultiplyexpression.rhs.accept(self)
    }
    
    func visit(qldivideexpression: QLDivideExpression) {
        print("Divide")
        qldivideexpression.lhs.accept(self)
        qldivideexpression.rhs.accept(self)
    }
    
    func visit(qladdexpression: QLAddExpression) {
        print("Add")
        qladdexpression.lhs.accept(self)
        qladdexpression.rhs.accept(self)
    }
    
    func visit(qlsubtractexpression: QLSubtractExpression) {
        print("Substract")
        qlsubtractexpression.lhs.accept(self)
        qlsubtractexpression.rhs.accept(self)
    }
    
    func visit(qlandexpression: QLAndExpression) {
        print("And")
        qlandexpression.lhs.accept(self)
        qlandexpression.rhs.accept(self)
    }
    
    func visit(qlorexpression: QLOrExpression) {
        print("Or")
        qlorexpression.lhs.accept(self)
        qlorexpression.rhs.accept(self)
    }
    
    // MARK: Literals.
    func visit(qlunknownliteral: QLUnknownLiteral) {
        print("-> Unknown literal")
    }
    
    func visit(qlbool: QLBool) {
        print("-> Boolean")
    }
    
    func visit(qlstring: QLString) {
        print("-> String")
    }
    
    func visit(qlinteger: QLInteger) {
        print("-> Integer")
    }
    
    func visit(qldate: QLDate) {
        print("-> Date")
    }
    
    func visit(qldecimal: QLDecimal) {
        print("-> Decimal")
    }
    
    func visit(qlmoney: QLMoney) {
        print("-> Money")
    }
    
}