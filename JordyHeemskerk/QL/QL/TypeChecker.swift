//
//  ConcreteASTVisitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class TypeChecker: FormVisitor, BlockVisitor, StatementVisitor, ExpressionVisitor {
    
    typealias FormReturnType = ()
    typealias BlockReturnType = ()
    typealias StatementReturnType = ()
    typealias ExpressionReturnType = Bool
    
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
    
    func visit(form: Form) -> FormReturnType {
        form.block.accept(self)
    }
    
    func visit(block: Block) -> BlockReturnType {
        block.statements.forEach {
            $0.accept(self)
        }
    }
    
    func visit(questionDeclaration: QuestionDeclaration) -> StatementReturnType {
        if let typeChecks = questionDeclaration.computation?.accept(self) {
            let computation = questionDeclaration.computation!
            if typeChecks && !questionDeclaration.type.compatible(computation, symbolTable: symbolTable) {
                semanticLog.logError(.ComputedTypeMismatch(identifier: questionDeclaration.identifier, expectedType: questionDeclaration.type, inferedType: computation.inferType(symbolTable)!, position: questionDeclaration.position))
            }
        }
    }
    
    func visit(ifStatement: IfStatement) -> StatementReturnType {
        if ifStatement.conditionClause.accept(self) {
            if !BooleanType().compatible(ifStatement.conditionClause, symbolTable: symbolTable) {
                semanticLog.logError(.ConditionTypeMismatch(inferedType: ifStatement.conditionClause.inferType(symbolTable)!, position: ifStatement.position))
            }
        }
        ifStatement.block.accept(self)
        ifStatement.elseClause?.accept(self)
    }
    
    func visit(elseIfStatement: ElseIfStatement) -> StatementReturnType {
        if let typeChecks = elseIfStatement.conditionClause?.accept(self) {
            let conditionClause = elseIfStatement.conditionClause!
            if typeChecks && !BooleanType().compatible(conditionClause, symbolTable: symbolTable) {
                semanticLog.logError(.ConditionTypeMismatch(inferedType: conditionClause.inferType(symbolTable)!, position: elseIfStatement.position))
            }
        }
        elseIfStatement.block.accept(self)
        elseIfStatement.elseClause?.accept(self)
    }
    
    func visit(addExpression: AddExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(addExpression)
    }
    
    func visit(subExpression: SubExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(subExpression)
    }
    
    func visit(mulExpression: MulExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(mulExpression)
    }
    
    func visit(divExpression: DivExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(divExpression)
    }
    
    func visit(ltExpression: LtExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(ltExpression)
    }
    
    func visit(letExpression: LetExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(letExpression)
    }
    
    func visit(gtExpression: GtExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(gtExpression)
    }
    
    func visit(getExpression: GetExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(getExpression)
    }
    
    func visit(eqExpression: EqExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(eqExpression)
    }
    
    func visit(neqExpression: NeqExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(neqExpression)
    }
    
    func visit(andExpression: AndExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(andExpression)
    }
    
    func visit(orExpression: OrExpression) -> ExpressionReturnType {
        return typeCheckBinaryExpression(orExpression)
    }
    
    func visit(notExpression: NotExpression) -> ExpressionReturnType {
        return typeCheckUnaryExpression(notExpression)
    }
    
    func visit(integerLiteral: IntegerLiteral) -> ExpressionReturnType {
        return true
    }
    
    func visit(booleanLiteral: BooleanLiteral) -> ExpressionReturnType {
        return true
    }
    
    func visit(floatLiteral: FloatLiteral) -> ExpressionReturnType {
        return true
    }
    
    func visit(variable: Variable) -> ExpressionReturnType {
        return variable.inferType(symbolTable) != nil
    }
    
    
    
    //MARK: Helper functions
    func typeCheckBinaryExpression(expression: BinaryExpression) -> Bool {
        guard expression.lhs.accept(self) && expression.rhs.accept(self) else {
            return false
        }
        if expression.lhs.inferType(symbolTable)?.add(expression.rhs, symbolTable: symbolTable) == nil {
            let lhsInferedType = expression.lhs.inferType(symbolTable)!
            let rhsInferedType = expression.rhs.inferType(symbolTable)!
            semanticLog.logError(SemanticError.BinaryOperatorTypeMismatch(lhsInferedType: lhsInferedType, rhsInferedType: rhsInferedType, op: expression, position: expression.position))
            return false
        }
        return true
    }
    
    func typeCheckUnaryExpression(expression: UnaryExpression) -> Bool {
        guard expression.operand.accept(self) else {
            return false
        }
        if expression.operand.inferType(symbolTable)?.not() == nil {
            let operandInferedType = expression.operand.inferType(symbolTable)!
            semanticLog.logError(SemanticError.UnaryOperatorTypeMismatch(operandInferedType: operandInferedType, op: expression, position: expression.position))
            return false
        }
        return true
    }
    
    
    
    /*
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
    }*/
    
}