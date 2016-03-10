//
//  TypeInferer.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation


internal class TypeInferer: QLNodeVisitor {
    typealias QLStatementVisitorParam   = Void?
    typealias QLExpressionVisitorParam  = Void?
    typealias QLLiteralVisitorParam     = Void?
    typealias QLTypeVisitorParam        = Void?
    typealias QLStatementVisitorReturn  = QLType
    typealias QLExpressionVisitorReturn = QLType
    typealias QLLiteralVisitorReturn    = QLType
    typealias QLTypeVisitorReturn       = QLType
    
    typealias GenericParam  = QLForm
    typealias GenericResult = SymbolTable
    
    private var symbolTable: SymbolTable = SymbolTable()
    private var errors: [SemanticError] = []
    
    
    func inferTypes(form: QLForm) throws -> SymbolTable {
        defer {
            resetInternals()
        }
        
        form.block.accept(self, param: nil)
        
        
        if errors.isEmpty {
            return symbolTable
        } else {
            throw SemanticErrorCollection(errors: errors)
        }
    }
}


// MARK: -  QLStatementVisitor conformance

extension TypeInferer {
    
    func visit(node: QLVariableQuestion, param: Void?) -> QLType {
        assignSymbol(node.identifier, symbol: (node.type, node))
        
        return node.type
    }
    
    func visit(node: QLComputedQuestion, param: Void?) -> QLType {
        let type = node.expression.accept(self, param: nil)
        
        assignSymbol(node.identifier, symbol: (type, node))
        
        return type
    }
    
    func visit(node: QLConditional, param: Void?) -> QLType {
        node.ifBlock.accept(self, param: param)
        
        return QLVoidType()
    }
    
    func visit(node: QLBlock, param: Void?) -> QLType {
        
        var unassignedQuestions = node.questions()
        var oldCount = 0
        
        // Until fixed point is reached assign types
        while !unassignedQuestions.isEmpty && unassignedQuestions.count != oldCount {
            oldCount = unassignedQuestions.count
            
            var newUnassigned = [QLQuestion]()
            
            for question in node.questions() {
                if question.accept(self, param: param) === QLUnknownType.self {
                    newUnassigned.append(question)
                }
            }
            
            unassignedQuestions = newUnassigned
        }
        
        // Errors for unresolved types
        for unassignedQuestion in unassignedQuestions {
            collectError(TypeInferenceError(description: "The type of \'\(unassignedQuestion.identifier.toString())\' is ambigious and could not be resolved."))
        }
        
        // Dive into next scope
        for conditional in node.conditionals() {
            conditional.accept(self, param: param)
        }
        
        return QLVoidType()
    }
}


// MARK: -  QLExpressionVisitor conformance

extension TypeInferer {
    
    func visit(node: QLVariable, param: Void?) -> QLType {
        return retrieveType(node)
    }
    
    func visit(node: QLLiteralExpression, param: Void?) -> QLType {
        return node.literal.accept(self, param: param)
    }
    
    func visit(node: QLNeg, param: Void?) -> QLType {
        return node.rhs.accept(self, param: param)
    }
    
    func visit(node: QLNot, param: Void?) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLAdd, param: Void?) -> QLType {
        if node.lhs.accept(self, param: param) === QLIntegerType.self && node.rhs.accept(self, param: param) === QLIntegerType.self {
            return QLIntegerType()
        }
        
        return QLFloatType()
    }
    
    func visit(node: QLSub, param: Void?) -> QLType {
        if node.lhs.accept(self, param: param) === QLIntegerType.self && node.rhs.accept(self, param: param) === QLIntegerType.self {
            return QLIntegerType()
        }
        
        return QLFloatType()
    }
    
    func visit(node: QLMul, param: Void?) -> QLType {
        return QLFloatType()
    }
    
    func visit(node: QLDiv, param: Void?) -> QLType {
        return QLFloatType()
    }
    
    func visit(node: QLPow, param: Void?) -> QLType {
        return QLFloatType()
    }
    
    func visit(node: QLGe, param: Void?) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLGt, param: Void?) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLLe, param: Void?) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLLt, param: Void?) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLEq, param: Void?) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLNe, param: Void?) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLAnd, param: Void?) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLOr, param: Void?) -> QLType {
        return QLBooleanType()
    }
}


// MARK: -  QLLiteralVisitor conformance

extension TypeInferer {
    
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


// MARK: -  QLTypeVisitor conformance

extension TypeInferer {
    
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


// MARK: -  Private methods

extension TypeInferer {
    
    private func resetInternals() {
        errors = []
    }
    
    private func assignSymbol(identifier: QLIdentifier, symbol: Symbol) {
        do {
            try symbolTable.assign(identifier.id, symbol: symbol)
        } catch _ as SemanticWarning {
            // no-op -> We do not care about warnings during type inference
        } catch let error as SemanticError {
            collectError(error)
        } catch let error {
            collectError(error)
        }
    }
    
    private func retrieveType(variable: QLVariable) -> QLType {
        guard let type = symbolTable.retrieveType(variable.id)
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