//
//  Questionnaire.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/03/16.
//
//

import Foundation

class Questionnaire: NSObject {
    let questions: [Question]
    
    init(questions: [Question]) {
        self.questions = questions
    }
}