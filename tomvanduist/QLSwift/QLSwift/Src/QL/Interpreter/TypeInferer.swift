//
//  TypeInferer.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation


class TypeInferer: TopDown {
    static let sharedInstance = TypeInferer()
    
    private var symbolTable = Map<QLType>()
    private var errors: [SemanticError] = []
    
    func inferType(statement: QLStatement, context: Context) -> QLType {
        return statement.accept(self, param: context)
    }
    
    func inferType(expression: QLExpression, context: Context) -> QLType {
        return expression.accept(self, param: context)
    }
    
    func inferTypes(form: QLForm, context: Context) throws -> Map<QLType> {
        defer {
            resetInternals()
        }
        
        form.block.accept(self, param: context)
        
        // Errors for unresolved types
        for (id, type) in symbolTable.getMap() {
            if type === QLUnknownType.self {
                collectError(TypeInferenceError(description: "The type of \'\(id)\' is ambigious and could not be resolved."))
            }
        }
        
        
        if errors.isEmpty {
            return symbolTable
        } else {
            throw SemanticErrorCollection(errors: errors)
        }
    }
}


// MARK: -  QLStatementVisitor conformance

extension TypeInferer {
    
    func visit(node: QLVariableQuestion, param context: Context) -> QLType {
        symbolTable.assign(node.identifier.id, value: node.type)
        
        return node.type
    }
    
    func visit(node: QLComputedQuestion, param context: Context) -> QLType {
        let type = node.expression.accept(self, param: context)
        
        symbolTable.assign(node.identifier.id, value: type)
        
        return type
    }
    
    func visit(node: QLBlock, param context: Context) -> QLType {
        
        var unassignedQuestions = node.questions()
        var oldCount = 0
        
        // Dive into next scope to resolve dependent types
        for conditional in node.conditionals() {
            conditional.accept(self, param: context)
        }
        
        // Until fixed point is reached assign types
        while !unassignedQuestions.isEmpty && unassignedQuestions.count != oldCount {
            oldCount = unassignedQuestions.count
            
            var newUnassigned = [QLQuestion]()
            
            for question in node.questions() {
                if question.accept(self, param: context) === QLUnknownType.self {
                    newUnassigned.append(question)
                }
            }
            
            unassignedQuestions = newUnassigned
        }
        
        // Assign unknown for still unresolved quesitons
        for unassignedQuestion in unassignedQuestions {
            symbolTable.assign(unassignedQuestion.identifier.id, value: QLUnknownType())
        }
        
        // Dive into next scope to resolve remaining types
        for conditional in node.conditionals() {
            conditional.accept(self, param: context)
        }
        
        return defaultReturn(nil, param: context)
    }
    
    func defaultReturn(statement: QLStatement?, param: Context) -> QLType {
        return QLVoidType()
    }
}


// MARK: -  QLExpressionVisitor conformance

extension TypeInferer {
    
    func visit(node: QLVariable, param context: Context) -> QLType {
        return retrieveType(node, context: context)
    }
    
    func visit(node: QLLiteralExpression, param context: Context) -> QLType {
        return node.literal.accept(self, param: context)
    }
    
    func visit(node: QLNeg, param context: Context) -> QLType {
        return node.rhs.accept(self, param: context)
    }
    
    func visit(node: QLNot, param context: Context) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLAdd, param context: Context) -> QLType {
        if node.lhs.accept(self, param: context) === QLIntegerType.self && node.rhs.accept(self, param: context) === QLIntegerType.self {
            return QLIntegerType()
        }
        
        return QLFloatType()
    }
    
    func visit(node: QLSub, param context: Context) -> QLType {
        if node.lhs.accept(self, param: context) === QLIntegerType.self && node.rhs.accept(self, param: context) === QLIntegerType.self {
            return QLIntegerType()
        }
        
        return QLFloatType()
    }
    
    func visit(node: QLMul, param context: Context) -> QLType {
        return QLFloatType()
    }
    
    func visit(node: QLDiv, param context: Context) -> QLType {
        return QLFloatType()
    }
    
    func visit(node: QLPow, param context: Context) -> QLType {
        return QLFloatType()
    }
    
    func visit(node: QLGe, param context: Context) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLGt, param context: Context) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLLe, param context: Context) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLLt, param context: Context) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLEq, param context: Context) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLNe, param context: Context) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLAnd, param context: Context) -> QLType {
        return QLBooleanType()
    }
    
    func visit(node: QLOr, param context: Context) -> QLType {
        return QLBooleanType()
    }
    
    func defaultReturn(expression: QLExpression, param: Context) -> QLType {
        return QLUnknownType()
    }
}


// MARK: -  QLLiteralVisitor conformance

extension TypeInferer {
    
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
    
    func defaultReturn(literal: QLLiteral, param: Context) -> QLType {
        fatalError("No generic default value - Visit literal node instead")
    }
}


// MARK: -  QLTypeVisitor conformance

extension TypeInferer {
    
    func defaultReturn(type: QLType,  param: Context) -> QLType {
        return type
    }
}


// MARK: -  Private methods

extension TypeInferer {
    
    private func resetInternals() {
        symbolTable = Map<QLType>()
        errors = []
    }
    
    private func retrieveType(variable: QLVariable, context: Context) -> QLType {
        if let type = symbolTable.retrieve(variable.id) {
            return type
        } else if let type = context.retrieveType(variable.id) {
            return type
        }
        
        return QLUnknownType()
    }
    
    private func collectError(error: SemanticError) {
        self.errors.append(error)
    }
    
    private func collectError(error: ErrorType) {
        self.errors.append(SystemError(error: error))
    }
}