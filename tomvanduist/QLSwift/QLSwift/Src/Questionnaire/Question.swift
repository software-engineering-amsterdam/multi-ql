//
//  Question.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/03/16.
//
//

import Foundation

class Question: NSObject {
    let context: QLContext
    let conditions: [QLExpression]
    let identifier: String
    let question: String
    let type: QLType
    let isComputed: Bool
    
    init(question: QLVariableQuestion, conditions: [QLExpression], context: QLContext) {
        self.context = context
        self.conditions = conditions
        self.identifier = question.identifier.id
        self.question = question.label
        self.type = question.type
        self.isComputed = false
    }
    
    init(question: QLComputedQuestion, type: QLType, conditions: [QLExpression], context: QLContext) {
        self.context = context
        self.conditions = conditions
        self.identifier = question.identifier.id
        self.question = question.label
        self.type = type
        self.isComputed = true
    }
    
    func enabled() -> Bool {
        for condition in conditions {
            guard let value = condition.eval(context) as? Bool
                else { return false }
            
            if !value {
                return false
            }
        }
        
        return true
    }
}