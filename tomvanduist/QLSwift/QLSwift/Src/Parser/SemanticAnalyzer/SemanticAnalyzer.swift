//
//  SemanticAnalyzer.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import Foundation


class SemanticAnalyzer {
    
    func analyze(form: QLForm) throws -> [SemanticWarning] {
        var warnings: [SemanticWarning] = []
        
        let typeChecker = TypeChecker()
        let error = SemanticErrorCollection(errors: [])
        
        
        tryAndCollect({
            try warnings += typeChecker.run(form)
        }, error: error)
        tryAndCollect({
            try CyclicDependencyChecker(symbolTable: typeChecker.symbolTable).run(form)
        }, error: error)
        
        
        if !error.errors.isEmpty {
            throw error
        }
        
        return warnings
    }
    
    private func tryAndCollect(f: (() throws -> Void), error: SemanticErrorCollection) {
        do {
            try f()
        } catch let e as SemanticErrorCollection {
            error.collectErrors(e.errors)
        } catch let e as SemanticError {
            error.collectError(e)
        } catch let e {
            error.collectError(SystemError(error: e))
        }
    }
}


// MARK: - TypeChecker

private class TypeChecker: QLStatementVisitor, QLExpressionVisitor, QLLiteralVisitor, QLTypeVisitor {
    
    typealias QLStatementVisitorParam   = Void?
    typealias QLExpressionVisitorParam  = Void?
    typealias QLLiteralVisitorParam     = Void?
    typealias QLTypeVisitorParam        = Void?
    typealias QLStatementVisitorReturn  = Void
    typealias QLExpressionVisitorReturn = QLType
    typealias QLLiteralVisitorReturn    = QLType
    typealias QLTypeVisitorReturn       = QLType
    
    private var symbolTable: SymbolTable = SymbolTable()
    private var scopedSymbolTable: SymbolTable = SymbolTable()
    private var errors: [SemanticError] = []
    private var warnings: [SemanticWarning] = []
    
    
    func run(form: QLForm) throws -> [SemanticWarning] {
        resetInternals()
        
        self.visit(form.block, param: nil)
        
        if errors.isEmpty {
            return warnings
        } else {
            throw SemanticErrorCollection(errors: errors)
        }
    }
    
    func getSymbolTable() -> SymbolTable {
        return symbolTable
    }
}


// MARK: TypeChecker - QLStatementVisitor conformance

extension TypeChecker {

    func visit(node: QLVariableQuestion, param: Void?) -> Void {
        do {
            try assignSymbol(node, type: node.type)
        } catch let warning as SemanticWarning {
            self.warnings.append(warning)
        } catch let e {
            collectError(e)
        }
    }
    
    func visit(node: QLComputedQuestion, param: Void?) -> Void {
        do {
            try assignSymbol(node, type: node.expression.accept(self, param: nil))
        } catch let warning as SemanticWarning {
            self.warnings.append(warning)
        } catch let e {
            collectError(e)
        }
    }
    
    func visit(node: QLConditional, param: Void?) -> Void {
        node.condition.accept(self, param: param)
        node.ifBlock.accept(self, param: param)
        
        if (node.condition.accept(self, param: nil) !== QLBooleanType.self) {
            collectError(TypeMismatchError(description: "If statement condition must be of type Bool: \(node.condition.toString())"))
        }
    }
    
    func visit(node: QLBlock, param: Void?) -> Void {
        scopedSymbolTable = SymbolTable(parent: scopedSymbolTable)
        
        for statement in node.block {
            statement.accept(self, param: param)
        }
        
        if let parent = scopedSymbolTable.parent {
            scopedSymbolTable = parent
        }
    }
}


// MARK: TypeChecker - QLExpressionVisitor conformance

extension TypeChecker {

    func visit(node: QLVariable, param: Void?) -> QLType {
        return retrieveSymbolType(node.id)
    }
    
    func visit(node: QLLiteralExpression, param: Void?) -> QLType {
        return node.literal.accept(self, param: param)
    }
    
    func collectUnaryTypeError(node: QLUnary) {
        collectError(TypeMismatchError(description: "Unary operator '\(node.toString())' cannot be applied to operand of type '\(node.rhs.toString())'!"))
    }
    
    func visit(node: QLNeg, param: Void?) -> QLType {
        if (node.rhs.accept(self, param: param) !== QLIntegerType.self) {
            collectUnaryTypeError(node)
        }
        
        return QLIntegerType()
    }
    
    func visit(node: QLNot, param: Void?) -> QLType {
        if (node.rhs.accept(self, param: param) !== QLBooleanType.self) {
            collectUnaryTypeError(node)
        }
        
        return QLBooleanType()
    }
    
    func collectBinaryTypeError(node: QLBinary) {
        collectError(TypeMismatchError(description: "Binary operator '\(node.toString())' cannot be applied to operands of type '\(node.lhs.toString())' and '\(node.rhs.toString())'!"))
    }

    func visitBinaryNumber(node: QLBinary) -> QLType {
        if (node.lhs.accept(self, param: nil) !== QLIntegerType.self || node.rhs.accept(self, param: nil) !== QLIntegerType.self) {
            collectBinaryTypeError(node)
        }
        
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
    
    func visitBinaryOrder(node: QLBinary) -> QLType {
        if (node.lhs.accept(self, param: nil) !== QLIntegerType.self || node.rhs.accept(self, param: nil) !== QLIntegerType.self) {
            collectBinaryTypeError(node)
        }
        
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
    
    func visitBinaryEq(node: QLBinary) -> QLType {
        if (node.lhs.accept(self, param: nil) !== node.rhs.accept(self, param: nil)) {
            collectBinaryTypeError(node)
        }
        
        return QLBooleanType()
    }
    
    func visit(node: QLEq, param: Void?) -> QLType {
        return visitBinaryEq(node)
    }
    
    func visit(node: QLNe, param: Void?) -> QLType {
        return visitBinaryEq(node)
    }
    
    func visitBinaryBool(node: QLBinary) -> QLType {
        if (node.lhs.accept(self, param: nil) !== QLBooleanType.self || node.rhs.accept(self, param: nil) !== QLBooleanType.self) {
            collectBinaryTypeError(node)
        }
        
        return QLBooleanType()
    }
    
    func visit(node: QLAnd, param: Void?) -> QLType {
        return visitBinaryBool(node)
    }
    
    func visit(node: QLOr, param: Void?) -> QLType {
        return visitBinaryBool(node)
    }
}


// MARK: TypeChecker - QLLiteralVisitor conformance

extension TypeChecker {
    
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


// MARK: TypeChecker - QLTypeVisitor conformance

extension TypeChecker {
    
    func visit(node: QLIntegerType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLStringType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLBooleanType, param: Void?) -> QLType {
        return node
    }
    
    func visit(node: QLUnknownType, param: Void?) -> QLType {
        return node
    }
}


// MARK: TypeChecker - Private methods

extension TypeChecker {
    
    private func resetInternals() {
        symbolTable = SymbolTable()
        scopedSymbolTable = SymbolTable()
        errors = []
        warnings = []
    }
    
    private func assignSymbol(question: QLQuestion, type: QLType) throws {
        _ = try? symbolTable.assign(question.identifier.id, object: (type, question))
        try scopedSymbolTable.assign(question.identifier.id, object: (type, question))
    }
    
    private func retrieveSymbolType(identifier: String) -> QLType {
        guard let type = scopedSymbolTable.retrieveType(identifier)
            else { return QLUnknownType() }
        
        return type
    }
    
    private func collectError(error: SemanticError) {
        self.errors.append(error)
    }
    
    private func collectError(error: ErrorType) {
        self.errors.append(SystemError(error: error))
    }
    
    private func collectWarning(warning: SemanticWarning) {
        self.warnings.append(warning)
    }
}


// MARK: - CyclicDependencyChecker

class CyclicDependencyChecker: QLStatementVisitor, QLExpressionVisitor, QLLiteralVisitor, QLTypeVisitor {
    
    typealias QLStatementVisitorParam   = [String]
    typealias QLExpressionVisitorParam  = [String]
    typealias QLLiteralVisitorParam     = [String]
    typealias QLTypeVisitorParam        = [String]
    typealias QLStatementVisitorReturn  = [SemanticError]
    typealias QLExpressionVisitorReturn = [SemanticError]
    typealias QLLiteralVisitorReturn    = [SemanticError]
    typealias QLTypeVisitorReturn       = [SemanticError]
    
    private let symbolTable: SymbolTable
    
    
    init(symbolTable: SymbolTable) {
        self.symbolTable = symbolTable
    }
    
    func run(form: QLForm) throws {
        let errors = self.visit(form.block, param: [])
        
        if !errors.isEmpty {
            throw SemanticErrorCollection(errors: errors)
        }
    }
}


// MARK: CyclicDependencyChecker - QLStatementVisitor conformance

extension CyclicDependencyChecker {
    
    func visit(node: QLVariableQuestion, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLComputedQuestion, param: [String]) -> [SemanticError] {
        var identifiers = param
        identifiers.append(node.identifier.id)
        
        return node.expression.accept(self, param: identifiers)
    }
    
    func visit(node: QLConditional, param: [String]) -> [SemanticError] {
        return node.ifBlock.accept(self, param: param)
    }
    
    func visit(node: QLBlock, param: [String]) -> [SemanticError] {
        var errors: [SemanticError] = []
        
        for statement in node.block {
            errors += statement.accept(self, param: param)
        }
        
        return errors
    }
}


// MARK: CyclicDependencyChecker - QLExpressionVisitor conformance

extension CyclicDependencyChecker {
    
    func visit(node: QLVariable, param: [String]) -> [SemanticError] {
        guard let question = symbolTable.retrieveQuestion(node.id)
            else { return [] }
        
        let current = question.identifier.id
        
        if introducesCycle(current, identifiers: param) {
            return [CyclomaticDependencyError(description: "A cyclomatic dependency exists for the identifier \(current). Path: \(constructPath(param + [current]))")]
        } else if question.isComputed() {
            return (question as! QLComputedQuestion).accept(self, param: param)
        } else {
            return []
        }
    }
    
    func visit(node: QLLiteralExpression, param: [String]) -> [SemanticError] {
        return node.literal.accept(self, param: param)
    }
    
    func visitUnary(unary: QLUnary, identifiers: [String]) -> [SemanticError] {
        return unary.rhs.accept(self, param: identifiers)
    }
    
    func visit(node: QLNeg, param: [String]) -> [SemanticError] {
        return visitUnary(node, identifiers: param)
    }
    
    func visit(node: QLNot, param: [String]) -> [SemanticError] {
        return visitUnary(node, identifiers: param)
    }
    
    func visitBinary(binary: QLBinary, identifiers: [String]) -> [SemanticError] {
        let lhs = binary.lhs.accept(self, param: identifiers)
        let rhs = binary.rhs.accept(self, param: identifiers)
        
        return lhs + rhs
    }
    
    func visit(node: QLAdd, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLSub, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLMul, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLDiv, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLPow, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLGe, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLGt, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLLe, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLLt, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLEq, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLNe, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLAnd, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
    
    func visit(node: QLOr, param: [String]) -> [SemanticError] {
        return visitBinary(node, identifiers: param)
    }
}


// MARK: CyclicDependencyChecker - QLLiteralVisitor conformance

extension CyclicDependencyChecker {
    
    func visit(node: QLIntegerLiteral, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLStringLiteral, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLBooleanLiteral, param: [String]) -> [SemanticError] {
        return []
    }
}


// MARK: CyclicDependencyChecker - QLTypeVisitor conformance

extension CyclicDependencyChecker {
    
    func visit(node: QLIntegerType, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLStringType, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLBooleanType, param: [String]) -> [SemanticError] {
        return []
    }
    
    func visit(node: QLUnknownType, param: [String]) -> [SemanticError] {
        return []
    }
}


// MARK: CyclicDependencyChecker - Private methods

extension CyclicDependencyChecker {
    
    private func introducesCycle(current: String, identifiers: [String]) -> Bool {
        return identifiers.indexOf(current) != nil
    }
    
    private func constructPath(identifiers: [String]) -> String {
        var path: String = ""
        for i in 0...identifiers.count-1 {
            path += identifiers[i]
            if i != identifiers.count-1 {
                 path += " -> "
            }
        }
        return path
    }
    
    private func asSemanticError(error: SemanticError) -> SemanticError {
        return error
    }
    
    private func asSemanticError(error: ErrorType) -> SemanticError {
        return SystemError(error: error)
    }
}
