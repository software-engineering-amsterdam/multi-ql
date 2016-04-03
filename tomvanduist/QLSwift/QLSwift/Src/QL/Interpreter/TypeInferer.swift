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
        
        let questions = QuestionExtracter().extract(form)
        
        inferQuestions(questions, context: context)
        
        if let errors = collectErrorsForUnresolved(questions) {
            throw SemanticErrorCollection(errors: errors)
        } else {
            return symbolTable
        }
    }
}


// MARK: -  QLStatementVisitor

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
    
    func defaultLeafResult(statement: QLStatement?, param: Context) -> QLType {
        return QLVoidType()
    }
}


// MARK: -  QLExpressionVisitor

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
    
    func defaultLeafResult(expression: QLExpression, param: Context) -> QLType {
        return QLUnknownType()
    }
}


// MARK: -  QLLiteralVisitor

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
    
    func defaultLeafResult(literal: QLLiteral, param: Context) -> QLType {
        fatalError("No generic default value - Visit literal node instead")
    }
}


// MARK: -  QLTypeVisitor

extension TypeInferer {
    
    func defaultLeafResult(type: QLType,  param: Context) -> QLType {
        return type
    }
}


// MARK: -  Private methods

extension TypeInferer {
    
    private func resetInternals() {
        symbolTable = Map<QLType>()
    }
    
    private func retrieveType(variable: QLVariable, context: Context) -> QLType {
        if let type = symbolTable.retrieve(variable.id) {
            return type
        } else if let type = context.retrieveType(variable.id) {
            return type
        }
        
        return QLUnknownType()
    }
    
    private func inferQuestions(questions: [QLQuestion], context: Context) {
        var unassignedQuestions = questions
        var oldCount = 0
        
        // Until fixed point is reached assign types
        while !unassignedQuestions.isEmpty && unassignedQuestions.count != oldCount {
            oldCount = unassignedQuestions.count
            
            var newUnassigned = [QLQuestion]()
            
            for question in unassignedQuestions {
                if question.accept(self, param: context) === QLUnknownType.self {
                    newUnassigned.append(question)
                }
            }
            
            unassignedQuestions = newUnassigned
        }
        
        // Assign unknown type for still unresolved questions
        for unassignedQuestion in unassignedQuestions {
            symbolTable.assign(unassignedQuestion.identifier.id, value: QLUnknownType())
        }
    }
    
    private func collectErrorsForUnresolved(questions: [QLQuestion]) -> [SemanticError]? {
        var errors = [SemanticError]()
        
        for (id, type) in symbolTable.getMap() {
            if type === QLUnknownType.self {
                errors.append(TypeInferenceError(description: "The type of \'\(id)\' is ambigious and could not be resolved."))
            }
        }
        
        return errors.isEmpty ? nil : errors
    }
}


private class QuestionExtracter: TopDownStatement {
    
    func extract(form: QLForm) -> [QLQuestion] {
        return form.block.accept(self, param: nil)
    }
    
    func visit(node: QLVariableQuestion, param: Void?) -> [QLQuestion] {
        return [node]
    }
    
    func visit(node: QLComputedQuestion, param: Void?) -> [QLQuestion] {
        return [node]
    }
    
    func visit(node: QLConditional, param: Void?) -> [QLQuestion] {
        return node.ifBlock.accept(self, param: param)
    }
    
    func visit(node: QLBlock, param: Void?) -> [QLQuestion] {
        var questions = [QLQuestion]()
        
        for statement in node.block {
            questions += statement.accept(self, param: param)
        }
        
        return questions
    }
    
    func defaultLeafResult(statement: QLStatement?, param: Void?) -> [QLQuestion] {
        return []
    }
}