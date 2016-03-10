//
//  QuestionnaireBuilder.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/03/16.
//
//

import Foundation

class QuestionnaireBuilder: NSObject, QLStatementVisitor {
    
    typealias QLStatementVisitorParam   = (conditions: [QLExpression], context: QLContext, symbolTable: SymbolTable)
    typealias QLStatementVisitorReturn  = [Question]
    
    
    func build(form: QLForm, symbolTable: SymbolTable) -> Questionnaire {
        let questions = form.block.accept(self, param: (conditions: [], context: QLContext(form: form), symbolTable: symbolTable))
        
        return Questionnaire(questions: questions)
    }
}


// MARK: - QLStatementVisitor conformance 

extension QuestionnaireBuilder {
    
    func visit(node: QLVariableQuestion, param: (conditions: [QLExpression], context: QLContext, symbolTable: SymbolTable)) -> [Question] {
        return [Question(question: node, conditions: param.conditions, context: param.context)]
    }
    
    func visit(node: QLComputedQuestion, param: (conditions: [QLExpression], context: QLContext, symbolTable: SymbolTable)) -> [Question] {
        guard let type = param.symbolTable.retrieveType(node.identifier.id)
            else { fatalError() }
        
        return [Question(question: node, type: type, conditions: param.conditions, context: param.context)]
    }
    
    func visit(node: QLConditional, var param: (conditions: [QLExpression], context: QLContext, symbolTable: SymbolTable)) -> [Question] {
        param.conditions = param.conditions + [node.condition]

        return node.ifBlock.accept(self, param: param)
    }
    
    func visit(node: QLBlock, param: (conditions: [QLExpression], context: QLContext, symbolTable: SymbolTable)) -> [Question] {
        var questions = [Question]()
        
        for statement in node.block {
            questions += statement.accept(self, param: param)
        }
        
        return questions
    }
}