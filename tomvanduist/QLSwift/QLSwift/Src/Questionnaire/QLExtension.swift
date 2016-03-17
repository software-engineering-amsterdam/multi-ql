//
//  QLExtension.swift
//  QLSwift
//
//  Created by Tom van Duist on 17/03/16.
//
//

import Foundation

extension QL {
    
    func toQuestionnaire() throws -> (Questionnaire, [SemanticWarning]) {
        let (form, warnings) = try self.toForm()
        let context = try Context(form: form)
        let questionnaire = QuestionnaireBuilder().build(form, context: context)
        
        return (questionnaire, warnings)
    }
}