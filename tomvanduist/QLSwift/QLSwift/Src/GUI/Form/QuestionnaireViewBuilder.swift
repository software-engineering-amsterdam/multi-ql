//
//  FormViewBuilder.swift
//  QLSwift
//
//  Created by Tom van Duist on 24/02/16.
//
//

import UIKit


class QuestionnaireViewBuilder {
    
    func build(questionnaire: Questionnaire) -> UIView {
        return createQuestionnaireView(questionnaire)
    }

    
    // MARK: - Private 
    
    private func createQuestionnaireView(questionnaire: Questionnaire) -> UIView {
        let questionnaireView = UIView()
        
        if let question = questionnaire.questions.first {
            var prevView = placeQuestion(question, superView: questionnaireView, prevView: nil, nextView: questionnaire.questions.count == 1 ? questionnaireView : nil)
            
            if questionnaire.questions.count > 1 {
                for i in 1...(questionnaire.questions.count-1) {
                    prevView = placeQuestion(questionnaire.questions[i], superView: questionnaireView, prevView: prevView, nextView: questionnaire.questions.count == i+1 ? questionnaireView : nil)
                }
            }
        }
        
        return questionnaireView
    }
    
    private func placeQuestion(question: Question, superView: UIView, prevView: UIView?, nextView: UIView?) -> UIView {
        let questionView = createQuestionView(question)
        
        superView.addSubview(questionView)
        
        questionView.snp_updateConstraints { [unowned superView, prevView] (make) -> Void in
            make.left.equalTo(superView.snp_left)
            make.right.equalTo(superView.snp_right)
            if let prevView = prevView {
                make.top.equalTo(prevView.snp_bottom)
            } else {
                make.top.equalTo(superView.snp_top)
            }
            if let nextView = nextView {
                make.bottom.equalTo(nextView.snp_bottom)
            }
        }
        
        return questionView
    }
    
    private func createQuestionView(question: Question) -> UIView {
        return QuestionView(layout: DefaultFormLayout().questionLayout, question: question)
    }
}