//
//  TypeChecker.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation


internal class TypeChecker: SemanticAnalysisRule, QLNodeVisitor {
    
    typealias QLStatementVisitorParam   = Void?
    typealias QLExpressionVisitorParam  = Void?
    typealias QLLiteralVisitorParam     = Void?
    typealias QLTypeVisitorParam        = Void?
    typealias QLStatementVisitorReturn  = QLType
    typealias QLExpressionVisitorReturn = QLType
    typealias QLLiteralVisitorReturn    = QLType
    typealias QLTypeVisitorReturn       = QLType
    
    private var symbolTable: SymbolTable!
    private var errors: [SemanticError] = []
    
    
    func run(form: QLForm, symbolTable: SymbolTable) -> SemanticAnalysisResult {
        resetInternals(symbolTable)
        
        checkTypes(form)
        
        return SemanticAnalysisResult(success: errors.isEmpty, warnings: [], errors: errors)
    }
}


// MARK: - QLStatementVisitor conformance

extension TypeChecker {
    
    func visit(node: QLVariableQuestion, param: Void?) -> QLType {
        return node.type
    }
    
    func visit(node: QLComputedQuestion, param: Void?) -> QLType {
        return node.expression.accept(self, param: nil)
    }
    
    func visit(node: QLConditional, param: Void?) -> QLType {
        node.condition.accept(self, param: param)
        node.ifBlock.accept(self, param: param)
        
        if (node.condition.accept(self, param: nil) !== QLBooleanType.self) {
            collectError(TypeMismatchError(description: "If statement condition must be of type Bool: \(node.condition.toString())"))
        }
        
        return QLVoidType()
    }
    
    func visit(node: QLBlock, param: Void?) -> QLType {
        for statement in node.block {
            statement.accept(self, param: param)
        }
        
        return QLVoidType()
    }
}


// MARK: - QLExpressionVisitor conformance

extension TypeChecker {
    
    func visit(node: QLVariable, param: Void?) -> QLType {
        return retrieveSymbolType(node.id)
    }
    
    func visit(node: QLLiteralExpression, param: Void?) -> QLType {
        return node.literal.accept(self, param: param)
    }
    
    private func collectUnaryTypeError(node: QLUnary) {
        let type = node.rhs.accept(self, param: nil)
        collectError(TypeMismatchError(description: "Unary operator '\(node.toString())' cannot be applied to operand of type '\(type.toString())'!"))
    }
    
    private func performUnaryPropagator(node: QLUnary, allower: AbstractAllowType) {
        let rightType = node.rhs.accept(self, param: nil)
        
        if !(allower.allowed(rightType)) {
            collectUnaryTypeError(node)
        }
    }
    
    func visit(node: QLNeg, param: Void?) -> QLType {
        performUnaryPropagator(node, allower: NumberAllowed())
        
        return QLIntegerType()
    }
    
    func visit(node: QLNot, param: Void?) -> QLType {
        performUnaryPropagator(node, allower: BoolAllowed())
        
        return QLBooleanType()
    }
    
    private func collectBinaryTypeError(node: QLBinary) {
        let leftType = node.lhs.accept(self, param: nil)
        let rightType = node.rhs.accept(self, param: nil)
        
        collectError(TypeMismatchError(description: "Binary operator '\(node.toString())' cannot be applied to operands of type '\(leftType.toString())' and '\(rightType.toString())'!"))
    }
    
    private func performBinaryPropagator(node: QLBinary, propagator: AbstractPropagator) {
        let lhsType = node.lhs.accept(self, param: nil)
        let rhsType = node.rhs.accept(self, param: nil)
        
        if !(propagator.propagade(lhsType).allowed(rhsType)) {
            collectBinaryTypeError(node)
        }
    }
    
    private func visitBinaryNumber(node: QLBinary) -> QLType {
        performBinaryPropagator(node, propagator: NumericOperationPropagator())
        
        return QLIntegerType()
    }
    
    func visit(node: QLAdd, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    func visit(node: QLSub, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    func visit(node: QLMul, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    func visit(node: QLDiv, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    func visit(node: QLPow, param: Void?) -> QLType {
        return visitBinaryNumber(node)
    }
    
    private func visitBinaryOrder(node: QLBinary) -> QLType {
        performBinaryPropagator(node, propagator: OrderOperationPropagator())
        
        return QLBooleanType()
    }
    
    func visit(node: QLGe, param: Void?) -> QLType {
        return visitBinaryOrder(node)
    }
    
    func visit(node: QLGt, param: Void?) -> QLType {
        return visitBinaryOrder(node)
    }
    
    func visit(node: QLLe, param: Void?) -> QLType {
        return visitBinaryOrder(node)
    }
    
    func visit(node: QLLt, param: Void?) -> QLType {
        return visitBinaryOrder(node)
    }
    
    private func visitBinaryEq(node: QLBinary) -> QLType {
        performBinaryPropagator(node, propagator: EqualityOperationPropagator())
        
        return QLBooleanType()
    }
    
    func visit(node: QLEq, param: Void?) -> QLType {
        return visitBinaryEq(node)
    }
    
    func visit(node: QLNe, param: Void?) -> QLType {
        return visitBinaryEq(node)
    }
    
    private func visitBinaryBool(node: QLBinary) -> QLType {
        performBinaryPropagator(node, propagator: BoolOperationPropagator())
        
        return QLBooleanType()
    }
    
    func visit(node: QLAnd, param: Void?) -> QLType {
        return visitBinaryBool(node)
    }
    
    func visit(node: QLOr, param: Void?) -> QLType {
        return visitBinaryBool(node)
    }
}


// MARK: - QLLiteralVisitor conformance

extension TypeChecker {
    
    func visit(node: QLFloatLiteral, param: Void?) -> QLType {
        return QLFloatType()
    }
    
    func visit(node: QLIntegerLiteral, param: Void?) -> QLType {
        return QLIntegerType()
    }
    
    func visit(node: QLStringLiteral, param: Void?) -> QLType {
        return QLStringType()
    }
    
    func visit(node: QLBooleanLiteral, param: Void?) -> QLType {
        return QLBooleanType()
    }
}


// MARK: - QLTypeVisitor conformance

extension TypeChecker {

    func visit(node: QLFloatType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLIntegerType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLStringType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLBooleanType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLVoidType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLUnknownType, param: Void?) -> QLType {
        return node
    }
}


// MARK: - Private methods

extension TypeChecker {
    
    private func resetInternals(symbolTable: SymbolTable) {
        self.symbolTable = symbolTable
        errors = []
    }
    
    private func checkTypes(form: QLForm) {
        self.visit(form.block, param: nil)
    }
    
    private func retrieveSymbolType(identifier: String) -> QLType {
        guard let type = symbolTable.retrieveType(identifier)
            else { return QLUnknownType() }
        
        return type
    }
    
    private func collectError(error: SemanticError) {
        self.errors.append(error)
    }
    
    private func collectError(error: ErrorType) {
        self.errors.append(SystemError(error: error))
    }
}

// MARK: - Double Dispatchers

/**
 * A propagator converts a type into a checker
 */
private protocol Propagator: QLTypeVisitor {
    func propagade(type: QLType) -> AbstractAllowType
}

/**
 * A checkers defines if a type is allowed
 */
private protocol AllowType: QLTypeVisitor {
    func allowed(type: QLType) -> Bool
}

private class AbstractPropagator: Propagator {
    func propagade(type: QLType) -> AbstractAllowType {
        return type.accept(self, param: nil)
    }
    
    func visit(node: QLStringType, param: Void?) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLIntegerType, param: Void?) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLFloatType, param: Void?) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLBooleanType, param: Void?) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLVoidType, param: Void?) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLUnknownType, param: Void?) -> AbstractAllowType {
        return AbstractAllowType()
    }
}

private class AbstractAllowType: AllowType {
    func allowed(type: QLType) -> Bool {
        return type.accept(self, param: nil)
    }
    
    func visit(node: QLStringType, param: Void?) -> Bool {
        return false
    }
    func visit(node: QLIntegerType, param: Void?) -> Bool {
        return false
    }
    func visit(node: QLFloatType, param: Void?) -> Bool {
        return false
    }
    func visit(node: QLBooleanType, param: Void?) -> Bool {
        return false
    }
    func visit(node: QLVoidType, param: Void?) -> Bool {
        return false
    }
    func visit(node: QLUnknownType, param: Void?) -> Bool {
        return false
    }
}

private class NumericOperationPropagator: AbstractPropagator {
    override private func visit(node: QLFloatType, param: Void?) -> AbstractAllowType {
        return NumberAllowed()
    }
    override private func visit(node: QLIntegerType, param: Void?) -> AbstractAllowType {
        return NumberAllowed()
    }
}
private class OrderOperationPropagator: NumericOperationPropagator {
}
private class BoolOperationPropagator: AbstractPropagator {
    override private func visit(node: QLBooleanType, param: Void?) -> AbstractAllowType {
        return BoolAllowed()
    }
}
private class EqualityOperationPropagator: AbstractPropagator {
    override func visit(node: QLStringType, param: Void?) -> AbstractAllowType {
        return StringAllowed()
    }
    override func visit(node: QLIntegerType, param: Void?) -> AbstractAllowType {
        return NumberAllowed()
    }
    override func visit(node: QLFloatType, param: Void?) -> AbstractAllowType {
        return NumberAllowed()
    }
    override func visit(node: QLBooleanType, param: Void?) -> AbstractAllowType {
        return BoolAllowed()
    }
}

private class NumberAllowed: AbstractAllowType {
    override private func visit(node: QLFloatType, param: Void?) -> Bool {
        return true
    }
    override private func visit(node: QLIntegerType, param: Void?) -> Bool {
        return true
    }
}
private class StringAllowed: AbstractAllowType {
    override private func visit(node: QLStringType, param: Void?) -> Bool {
        return true
    }
}
private class BoolAllowed: AbstractAllowType {
    override private func visit(node: QLBooleanType, param: Void?) -> Bool {
        return true
    }
}