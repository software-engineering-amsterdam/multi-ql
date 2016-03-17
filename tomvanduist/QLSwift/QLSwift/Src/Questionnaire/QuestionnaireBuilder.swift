//
//  QuestionnaireBuilder.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/03/16.
//
//

import Foundation

class QuestionnaireBuilder: NSObject, TopDownStatement {
    
    func build(form: QLForm, context: Context) -> Questionnaire {
        let questions = form.block.accept(self, param: (conditions: [], context: context))
        
        return Questionnaire(title: form.identifier.id, questions: questions)
    }
}


// MARK: - QLStatementVisitor conformance 

extension QuestionnaireBuilder {
    
    func visit(node: QLVariableQuestion, param: (conditions: [QLExpression], context: Context)) -> [Question] {
        return [Question(question: node, conditions: param.conditions, context: param.context)]
    }
    
    func visit(node: QLComputedQuestion, param: (conditions: [QLExpression], context: Context)) -> [Question] {
        guard let type = param.context.retrieveType(node.identifier.id)
            else { fatalError("This is not the context you are looking for") }
        
        return [Question(question: node, type: type, conditions: param.conditions, context: param.context)]
    }
    
    func visit(node: QLConditional, var param: (conditions: [QLExpression], context: Context)) -> [Question] {
        param.conditions = param.conditions + [node.condition]

        return node.ifBlock.accept(self, param: param)
    }
    
    func visit(node: QLBlock, param: (conditions: [QLExpression], context: Context)) -> [Question] {
        var questions = [Question]()
        
        for statement in node.block {
            questions += statement.accept(self, param: param)
        }
        
        return questions
    }
    
    func defaultReturn(statement: QLStatement?, param: (conditions: [QLExpression], context: Context)) -> [Question] {
        return []
    }
}