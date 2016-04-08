//
//  Form.swift
//  QLSwift
//
//  Created by Julian Jansen on 04-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class Form {

    private var formName = String()
    private var questions = Array<QLQuestion>()
    private var variableToValue = Dictionary<String, QLLiteral>()
    private var variableToExpression = Dictionary<String, QLExpression>()
    
    func setName(name: String) {
        formName = name
    }
    
    func getName() -> String {
        return formName
    }
    
    func getQuestions() -> Array<QLQuestion> {
        return questions
    }
    
    func getQuestionsCount() -> Int {
        return questions.count
    }
    
    func addQuestion(question: QLQuestion) {
        questions.append(question)
        addVariable(question.name, type: question.type)
        
        if question.expression != nil {
            addExpression(question.name, expr: question.expression!)
        }
    }
    
    func clearQuestions() {
        questions = []
    }
    
    func getValue(name: String) -> QLLiteral {
        return variableToValue[name]!
    }
    
    func updateValue(name: String, value: QLLiteral) {
        variableToValue[name] = value
        evaluateExpressions()
    }
    
    private func addVariable(name: String, type: QLType) {
        if (variableToValue[name] == nil) {
            switch type {
            case QLIntegerType(): variableToValue[name] = QLIntegerLiteral(integer: 0)
            case QLBoolType(): variableToValue[name] = QLBoolLiteral(boolean: false)
            case QLStringType(): variableToValue[name] = QLStringLiteral(string: "")
            default: AppLogger.sharedInstance.logError(EnvironmentError(message: "Unknown variable type"))
            }
        }
    }
    
    private func addExpression(name: String, expr: QLExpression) {
        variableToExpression[name] = expr
    }
    
    func evaluateExpressions() {
        
        let evaluator = QLExpressionEvaluator(form: self)
        
        for (name, expr) in variableToExpression {
            let oldLiteral = getValue(name)


            if (oldLiteral.type == .Integer) {
                let temp = oldLiteral as! QLIntegerLiteral
                let oldValue = temp.value
                let newValue = expr.accept(evaluator) as! Int

                
                if (oldValue != newValue) {
                    updateValue(name, value: QLIntegerLiteral(integer: newValue))
                }
                
            } else if (oldLiteral.type == .Boolean) {
                let temp = oldLiteral as! QLBoolLiteral
                let oldValue = temp.value
                let newValue = expr.accept(evaluator) as! Bool
                
                if (oldValue != newValue) {
                    updateValue(name, value: QLBoolLiteral(boolean: newValue))
                }
                
            } else {
                let temp = oldLiteral as! QLStringLiteral
                let oldValue = temp.value
                let newValue = expr.accept(evaluator) as! String

                
                if (oldValue != newValue) {
                    updateValue(name, value: QLStringLiteral(string: newValue))
                }
            }
        }
    }
}