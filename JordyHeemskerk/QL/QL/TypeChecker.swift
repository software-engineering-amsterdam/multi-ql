//
//  ConcreteASTVisitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class TypeChecker: BaseASTVisitor {
    
    var symbolTable = SymbolTable()
    var warnings = [String]()
    var errors = [String]()
    
    private init(symbolTable: SymbolTable, warnings: [String], errors: [String]) {
        self.symbolTable = symbolTable
        self.warnings = warnings
        self.errors = errors
    }
    
    static func check(form: Form, withSymbolTable symbolTable: SymbolTable, warnings: [String], andErrors errors: [String]) -> (symbolTable: SymbolTable, warnings: [String], errors: [String]) {
        let typeChecker = TypeChecker(symbolTable: symbolTable, warnings: warnings, errors: errors)
        typeChecker.visit(form)
        return (symbolTable: typeChecker.symbolTable, warnings: typeChecker.warnings, errors: typeChecker.errors)
    }
    
    override func visit(questionDeclaration: QuestionDeclaration) {
        guard let computation = questionDeclaration.computation else {
            return
        }
        if !questionDeclaration.type.compatible(computation, symbolTable: symbolTable) {
            errors.append("Computed type of variable '\(questionDeclaration.identifier)' does not match declaration, expected \(questionDeclaration.type) but got \(computation.inferType(symbolTable))")
        }
    }
    
    override func visit(ifStatement: IfStatement) {
        let expected = BooleanType()
        if !expected.compatible(ifStatement.conditionClause, symbolTable: symbolTable) {
            errors.append("Condition type is not of type \(expected), expected \(expected) but got \(ifStatement.conditionClause.inferType(symbolTable))")
        }
        ifStatement.block.accept(self)
        ifStatement.elseClause?.accept(self)
    }
    
    override func visit(elseIfStatement: ElseIfStatement) {
        let expected = BooleanType()
        if let conditionClause = elseIfStatement.conditionClause {
            if !expected.compatible(conditionClause, symbolTable: symbolTable) {
                errors.append("Condition type is not of type \(expected), expected \(expected) but got \(conditionClause.inferType(symbolTable))")
            }
        }
        elseIfStatement.block.accept(self)
        elseIfStatement.elseClause?.accept(self)
    }
    
}