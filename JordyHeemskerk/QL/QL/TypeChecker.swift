//
//  ConcreteASTVisitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class TypeChecker: BaseASTVisitor {
    
    var symbolTable: SymbolTable
    var semanticLog: SemanticLog
    
    private init(symbolTable: SymbolTable, semanticLog: SemanticLog) {
        self.symbolTable = symbolTable
        self.semanticLog = semanticLog
    }
    
    static func check(form: Form, withSymbolTable symbolTable: SymbolTable, andSemanticLog semanticLog: SemanticLog) -> (symbolTable: SymbolTable, semanticLog: SemanticLog) {
        let typeChecker = TypeChecker(symbolTable: symbolTable, semanticLog: semanticLog)
        typeChecker.visit(form)
        return (symbolTable: typeChecker.symbolTable, semanticLog: typeChecker.semanticLog)
    }
    
    override func visit(questionDeclaration: QuestionDeclaration) {
        guard let computation = questionDeclaration.computation else {
            return
        }
        if !questionDeclaration.type.compatible(computation, symbolTable: symbolTable) {
            semanticLog.logError(.ComputedTypeMismatch(identifier: questionDeclaration.identifier, expectedType: questionDeclaration.type, inferedType: computation.inferType(symbolTable), position: questionDeclaration.position))
        }
    }
    
    override func visit(ifStatement: IfStatement) {
        if !BooleanType().compatible(ifStatement.conditionClause, symbolTable: symbolTable) {
            semanticLog.logError(.ConditionTypeMismatch(inferedType: ifStatement.conditionClause.inferType(symbolTable), position: ifStatement.position))
        }
        ifStatement.block.accept(self)
        ifStatement.elseClause?.accept(self)
    }
    
    override func visit(elseIfStatement: ElseIfStatement) {
        if let conditionClause = elseIfStatement.conditionClause {
            if !BooleanType().compatible(conditionClause, symbolTable: symbolTable) {
                semanticLog.logError(.ConditionTypeMismatch(inferedType: conditionClause.inferType(symbolTable), position: elseIfStatement.position))
            }
        }
        elseIfStatement.block.accept(self)
        elseIfStatement.elseClause?.accept(self)
    }
    
}