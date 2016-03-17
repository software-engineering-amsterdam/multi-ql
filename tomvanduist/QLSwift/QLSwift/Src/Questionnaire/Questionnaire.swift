//
//  Questionnaire.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/03/16.
//
//

import Foundation

class Questionnaire: NSObject {
    let title: String
    let questions: [Question]
    
    init(title: String, questions: [Question]) {
        self.title = title
        self.questions = questions
    }
}