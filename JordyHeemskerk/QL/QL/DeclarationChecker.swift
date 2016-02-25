//
//  ConcreteASTVisitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class DeclarationChecker: BaseASTVisitor {
    
    var symbolTable = SymbolTable()
    var warnings = [String]()
    var errors = [String]()
    
    static func check(form: Form) -> (symbolTable: SymbolTable, warnings: [String], errors: [String]) {
        let declarationChecker = DeclarationChecker()
        declarationChecker.visit(form)
        return (symbolTable: declarationChecker.symbolTable, warnings: declarationChecker.warnings, errors: declarationChecker.errors)
    }
    
    override func visit(questionDeclaration: QuestionDeclaration) {
        questionDeclaration.computation?.accept(self)
        guard !symbolTable.isVariableDefined(questionDeclaration.identifier) else {
            errors.append("Variable \(questionDeclaration.identifier) already defined.")
            return;
        }
        symbolTable.defineVariable(questionDeclaration.identifier, type: questionDeclaration.type)
    }
    
}