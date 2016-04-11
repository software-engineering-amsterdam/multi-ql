//
//  QLTypeChecker.swift
//  QLSwift
//
//  Created by Julian Jansen on 10-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class QLTypeChecker: TypeVisitor {
    
    private let symbolTable = SymbolTable()
    private var questionLabels = Array<String>()
    
    // MARK: Type check methods
    
    func typeCheckExpression(expr: QLExpression, expectedType: QLType) {
        let exprType = expr.accept(self)
        
        if (exprType != expectedType) {
            AppLogger.sharedInstance.logError(TypeError(message: "Expected \(expectedType.description) in expression, got \(exprType.description)."))
        }
    }
    
    func typeCheckExpressions(lhs: QLExpression, rhs: QLExpression, expectedType: QLType) {
        let lhsType = lhs.accept(self)
        let rhsType = rhs.accept(self)
        
        if (lhsType != expectedType) {
            AppLogger.sharedInstance.logError(TypeError(message: "Expected \(expectedType.description) on lhs of expression, got \(lhsType.description)."))
        }
        
        if (rhsType != expectedType) {
            AppLogger.sharedInstance.logError(TypeError(message: "Expected \(expectedType.description) on rhs of expression, got \(rhsType.description)."))
        }
    }
    
    func typeCheckEquality(lhs: QLExpression, rhs: QLExpression) {
        let lhsType = lhs.accept(self)
        let rhsType = rhs.accept(self)
        
        if (lhsType != rhsType) {
            AppLogger.sharedInstance.logError(TypeError(message: "Cannot compare for (in)equality between \(lhsType.description) and \(rhsType.description)."))
        }
    }
    
    private func checkDuplicateLabel(newLabel: String) {
        for existingLabel in questionLabels {
            if (existingLabel == newLabel) {
                AppLogger.sharedInstance.logWarning(DuplicateWarning(message: "Label \(newLabel) already exists."))
                break
            }
        }
    }
    
    // MARK: Visitors
    
    func visit(qlform: QLForm) -> QLUnknownType {
        for statement in qlform.codeBlock {
            statement.accept(self)
        }
        
        return QLUnknownType()
    }

    func visit(qlquestion: QLQuestion) -> QLUnknownType {
        do {
            try symbolTable.addSymbol(qlquestion.name, qlType: qlquestion.type)
            
            checkDuplicateLabel(qlquestion.label)
            questionLabels.append(qlquestion.label)
            
            if (qlquestion.expression != nil) {
                typeCheckExpression(qlquestion.expression!, expectedType: qlquestion.type)
            }
        } catch {
            AppLogger.sharedInstance.logError(error)
        }
        
        return QLUnknownType()
    }

    func visit(qlifstatement: QLIfStatement) -> QLUnknownType {
        typeCheckExpression(qlifstatement.condition, expectedType: QLBoolType())
        
        for statement in qlifstatement.codeBlock {
            statement.accept(self)
        }
        
        return QLUnknownType()
    }
    
    func visit(qlvariable: QLVariable) -> QLType {
        do {
            return try symbolTable.getSymbol(qlvariable.name)
        } catch {
            AppLogger.sharedInstance.logError(error)
            return QLUnknownType()
        }
    }
    
    // MARK: Expressions
    
    func visit(expr: QLNotExpression) -> QLBoolType {
        typeCheckExpression(expr.expression, expectedType: QLBoolType())
        return QLBoolType()
    }
        
    func visit(expr: QLGreaterThanExpression) -> QLBoolType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLIntegerType())
        return QLBoolType()
    }
    
    func visit(expr: QLSmallerThanExpression) -> QLBoolType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLIntegerType())
        return QLBoolType()
    }
    
    func visit(expr: QLGreaterOrIsExpression) -> QLBoolType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLIntegerType())
        return QLBoolType()
    }
    
    func visit(expr: QLSmallerOrIsExpression) -> QLBoolType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLIntegerType())
        return QLBoolType()
    }
    
    func visit(expr: QLIsNotExpression) -> QLBoolType {
        typeCheckEquality(expr.lhs, rhs: expr.rhs)
        return QLBoolType()
    }
    
    func visit(expr: QLIsExpression) -> QLBoolType {
        typeCheckEquality(expr.lhs, rhs: expr.rhs)
        return QLBoolType()
    }
    
    func visit(expr: QLMultiplyExpression) -> QLIntegerType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLIntegerType())
        return QLIntegerType()
    }
    
    func visit(expr: QLDivideExpression) -> QLIntegerType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLIntegerType())
        return QLIntegerType()
    }
    
    func visit(expr: QLAddExpression) -> QLIntegerType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLIntegerType())
        return QLIntegerType()
    }
    
    func visit(expr: QLSubtractExpression) -> QLIntegerType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLIntegerType())
        return QLIntegerType()
    }
    
    func visit(expr: QLAndExpression) -> QLBoolType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLBoolType())
        return QLBoolType()
    }
    
    func visit(expr: QLOrExpression) -> QLBoolType {
        typeCheckExpressions(expr.lhs, rhs: expr.rhs, expectedType: QLBoolType())
        return QLBoolType()
    }
    
    // MARK: Literals
    
    func visit(qlbool: QLBoolLiteral) -> QLBoolType {
        return QLBoolType()
    }
    
    func visit(qlstring: QLStringLiteral) -> QLStringType {
        return QLStringType()
    }
    
    func visit(qlinteger: QLIntegerLiteral) -> QLIntegerType {
        return QLIntegerType()
    }
}