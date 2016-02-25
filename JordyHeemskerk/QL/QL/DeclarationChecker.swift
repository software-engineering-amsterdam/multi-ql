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
    var semanticLog = SemanticLog()
    
    static func check(form: Form) -> (symbolTable: SymbolTable, semanticLog: SemanticLog) {
        let declarationChecker = DeclarationChecker()
        declarationChecker.visit(form)
        return (symbolTable: declarationChecker.symbolTable, semanticLog: declarationChecker.semanticLog)
    }
    
    override func visit(questionDeclaration: QuestionDeclaration) {
        questionDeclaration.computation?.accept(self)
        guard !symbolTable.isVariableDefined(questionDeclaration.identifier) else {
            semanticLog.logError(.VariableAlreadyDefined(identifier: questionDeclaration.identifier))
            return;
        }
        symbolTable.defineVariable(questionDeclaration.identifier, type: questionDeclaration.type)
    }
    
}