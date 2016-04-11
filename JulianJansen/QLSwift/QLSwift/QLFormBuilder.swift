//
//  QLFormBuilder.swift
//  QLSwift
//
//  Created by Julian Jansen on 07-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

class QLFormBuilder: FormBuilderVisitor {
    
    private let form: Form
    
    init(form: Form) {
        self.form = form
    }
    
    func visit(qlform: QLForm) {
        
        form.setName(qlform.formName)
        
        for statement in qlform.codeBlock {
            statement.accept(self)
        }
    }
    
    func visit(qlquestion: QLQuestion) {
        form.addQuestion(qlquestion)
    }
    
    func visit(qlifstatement: QLIfStatement) {
        let evaluator = QLExpressionEvaluator(form: form)
        let condition = qlifstatement.condition.accept(evaluator) as! Bool

        if (condition) {
            for statement in qlifstatement.codeBlock {
                statement.accept(self)
            }
        }
    }
}