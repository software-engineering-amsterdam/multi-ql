//
//  TypeChecker.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation


internal class TypeChecker: SemanticAnalysisRule, QLNodeVisitor {
//    private var symbolTable: SymbolTable!
    private var errors: [SemanticError] = []
    
    
    func run(form: QLForm, context: Context) -> SemanticAnalysisResult {
        resetInternals()
        
        checkTypes(form, context: context)
        
        return SemanticAnalysisResult(success: errors.isEmpty, warnings: [], errors: errors)
    }
}


// MARK: - QLStatementVisitor conformance

extension TypeChecker {
    
    func visit(node: QLVariableQuestion, param context: Context) -> QLType {
        return node.type
    }
    
    func visit(node: QLComputedQuestion, param context: Context) -> QLType {
        return node.expression.accept(self, param: context)
    }
    
    func visit(node: QLConditional, param context: Context) -> QLType {
        node.condition.accept(self, param: context)
        node.ifBlock.accept(self, param: context)
        
        if (node.condition.accept(self, param: context) !== QLBooleanType.self) {
            collectError(TypeMismatchError(description: "If statement condition must be of type Bool: \(node.condition.toString())"))
        }
        
        return QLVoidType()
    }
    
    func visit(node: QLBlock, param context: Context) -> QLType {
        for statement in node.block {
            statement.accept(self, param: context)
        }
        
        return QLVoidType()
    }
}


// MARK: - QLExpressionVisitor conformance

extension TypeChecker {
    
    func visit(node: QLVariable, param context: Context) -> QLType {
        return retrieveSymbolType(node.id, context: context)
    }
    
    func visit(node: QLLiteralExpression, param context: Context) -> QLType {
        return node.literal.accept(self, param: context)
    }
    
    private func collectUnaryTypeError(node: QLUnary, context: Context) {
        let type = node.rhs.accept(self, param: context)
        collectError(TypeMismatchError(description: "Unary operator '\(node.toString())' cannot be applied to operand of type '\(type.toString())'!"))
    }
    
    private func performUnaryPropagator(node: QLUnary, allower: AbstractAllowType, context: Context) {
        let rightType = node.rhs.accept(self, param: context)
        
        if !(allower.allowed(rightType, context: context)) {
            collectUnaryTypeError(node, context: context)
        }
    }
    
    func visit(node: QLNeg, param context: Context) -> QLType {
        performUnaryPropagator(node, allower: NumberAllowed(), context: context)
        
        return QLIntegerType()
    }
    
    func visit(node: QLNot, param context: Context) -> QLType {
        performUnaryPropagator(node, allower: BoolAllowed(), context: context)
        
        return QLBooleanType()
    }
    
    private func collectBinaryTypeError(node: QLBinary, context: Context) {
        let leftType = node.lhs.accept(self, param: context)
        let rightType = node.rhs.accept(self, param: context)
        
        collectError(TypeMismatchError(description: "Binary operator '\(node.toString())' cannot be applied to operands of type '\(leftType.toString())' and '\(rightType.toString())'!"))
    }
    
    private func performBinaryPropagator(node: QLBinary, propagator: AbstractPropagator, context: Context) {
        let lhsType = node.lhs.accept(self, param: context)
        let rhsType = node.rhs.accept(self, param: context)
        
        if !(propagator.propagade(lhsType, context: context).allowed(rhsType, context: context)) {
            collectBinaryTypeError(node, context: context)
        }
    }
    
    private func visitBinaryNumber(node: QLBinary, context: Context) -> QLType {
        performBinaryPropagator(node, propagator: NumericOperationPropagator(), context: context)
        
        return QLIntegerType()
    }
    
    func visit(node: QLAdd, param context: Context) -> QLType {
        return visitBinaryNumber(node, context: context)
    }
    
    func visit(node: QLSub, param context: Context) -> QLType {
        return visitBinaryNumber(node, context: context)
    }
    
    func visit(node: QLMul, param context: Context) -> QLType {
        return visitBinaryNumber(node, context: context)
    }
    
    func visit(node: QLDiv, param context: Context) -> QLType {
        return visitBinaryNumber(node, context: context)
    }
    
    func visit(node: QLPow, param context: Context) -> QLType {
        return visitBinaryNumber(node, context: context)
    }
    
    private func visitBinaryOrder(node: QLBinary, context: Context) -> QLType {
        performBinaryPropagator(node, propagator: OrderOperationPropagator(), context: context)
        
        return QLBooleanType()
    }
    
    func visit(node: QLGe, param context: Context) -> QLType {
        return visitBinaryOrder(node, context: context)
    }
    
    func visit(node: QLGt, param context: Context) -> QLType {
        return visitBinaryOrder(node, context: context)
    }
    
    func visit(node: QLLe, param context: Context) -> QLType {
        return visitBinaryOrder(node, context: context)
    }
    
    func visit(node: QLLt, param context: Context) -> QLType {
        return visitBinaryOrder(node, context: context)
    }
    
    private func visitBinaryEq(node: QLBinary, context: Context) -> QLType {
        performBinaryPropagator(node, propagator: EqualityOperationPropagator(), context: context)
        
        return QLBooleanType()
    }
    
    func visit(node: QLEq, param context: Context) -> QLType {
        return visitBinaryEq(node, context: context)
    }
    
    func visit(node: QLNe, param context: Context) -> QLType {
        return visitBinaryEq(node, context: context)
    }
    
    private func visitBinaryBool(node: QLBinary, context: Context) -> QLType {
        performBinaryPropagator(node, propagator: BoolOperationPropagator(), context: context)
        
        return QLBooleanType()
    }
    
    func visit(node: QLAnd, param context: Context) -> QLType {
        return visitBinaryBool(node, context: context)
    }
    
    func visit(node: QLOr, param context: Context) -> QLType {
        return visitBinaryBool(node, context: context)
    }
}


// MARK: - QLLiteralVisitor conformance

extension TypeChecker {
    
    func visit(node: QLFloatLiteral, param context: Context) -> QLType {
        return QLFloatType()
    }
    
    func visit(node: QLIntegerLiteral, param context: Context) -> QLType {
        return QLIntegerType()
    }
    
    func visit(node: QLStringLiteral, param context: Context) -> QLType {
        return QLStringType()
    }
    
    func visit(node: QLBooleanLiteral, param context: Context) -> QLType {
        return QLBooleanType()
    }
}


// MARK: - QLTypeVisitor conformance

extension TypeChecker {

    func visit(node: QLFloatType, param context: Context) -> QLType {
        return node
    }
    
    func visit(node: QLIntegerType, param context: Context) -> QLType {
        return node
    }
    
    func visit(node: QLStringType, param context: Context) -> QLType {
        return node
    }
    
    func visit(node: QLBooleanType, param context: Context) -> QLType {
        return node
    }
    
    func visit(node: QLVoidType, param context: Context) -> QLType {
        return node
    }
    
    func visit(node: QLUnknownType, param context: Context) -> QLType {
        return node
    }
}


// MARK: - Private methods

extension TypeChecker {
    
    private func resetInternals() {
        errors = []
    }
    
    private func checkTypes(form: QLForm, context: Context) {
        self.visit(form.block, param: context)
    }
    
    private func retrieveSymbolType(identifier: String, context: Context) -> QLType {
        guard let type = context.retrieveType(identifier)
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
    func propagade(type: QLType, context: Context) -> AbstractAllowType
}

/**
 * A checkers defines if a type is allowed
 */
private protocol AllowType: QLTypeVisitor {
    func allowed(type: QLType, context: Context) -> Bool
}

private class AbstractPropagator: Propagator {
    func propagade(type: QLType, context: Context) -> AbstractAllowType {
        return type.accept(self, param: context)
    }
    
    func visit(node: QLStringType, param context: Context) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLIntegerType, param context: Context) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLFloatType, param context: Context) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLBooleanType, param context: Context) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLVoidType, param context: Context) -> AbstractAllowType {
        return AbstractAllowType()
    }
    func visit(node: QLUnknownType, param context: Context) -> AbstractAllowType {
        return AbstractAllowType()
    }
}

private class AbstractAllowType: AllowType {
    func allowed(type: QLType, context: Context) -> Bool {
        return type.accept(self, param: context)
    }
    
    func visit(node: QLStringType, param context: Context) -> Bool {
        return false
    }
    func visit(node: QLIntegerType, param context: Context) -> Bool {
        return false
    }
    func visit(node: QLFloatType, param context: Context) -> Bool {
        return false
    }
    func visit(node: QLBooleanType, param context: Context) -> Bool {
        return false
    }
    func visit(node: QLVoidType, param context: Context) -> Bool {
        return false
    }
    func visit(node: QLUnknownType, param context: Context) -> Bool {
        return false
    }
}

private class NumericOperationPropagator: AbstractPropagator {
    override private func visit(node: QLFloatType, param context: Context) -> AbstractAllowType {
        return NumberAllowed()
    }
    override private func visit(node: QLIntegerType, param context: Context) -> AbstractAllowType {
        return NumberAllowed()
    }
}
private class OrderOperationPropagator: NumericOperationPropagator {
}
private class BoolOperationPropagator: AbstractPropagator {
    override private func visit(node: QLBooleanType, param context: Context) -> AbstractAllowType {
        return BoolAllowed()
    }
}
private class EqualityOperationPropagator: AbstractPropagator {
    override func visit(node: QLStringType, param context: Context) -> AbstractAllowType {
        return StringAllowed()
    }
    override func visit(node: QLIntegerType, param context: Context) -> AbstractAllowType {
        return NumberAllowed()
    }
    override func visit(node: QLFloatType, param context: Context) -> AbstractAllowType {
        return NumberAllowed()
    }
    override func visit(node: QLBooleanType, param context: Context) -> AbstractAllowType {
        return BoolAllowed()
    }
}

private class NumberAllowed: AbstractAllowType {
    override private func visit(node: QLFloatType, param context: Context) -> Bool {
        return true
    }
    override private func visit(node: QLIntegerType, param context: Context) -> Bool {
        return true
    }
}
private class StringAllowed: AbstractAllowType {
    override private func visit(node: QLStringType, param context: Context) -> Bool {
        return true
    }
}
private class BoolAllowed: AbstractAllowType {
    override private func visit(node: QLBooleanType, param context: Context) -> Bool {
        return true
    }
}