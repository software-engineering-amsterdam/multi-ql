//
//  SemanticWarnings.swift
//  QL
//
//  Created by Jordy Heemskerk on 25/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

enum SemanticWarning: SemanticLoggable {
    
    case DuplicateQuestion(question: String)
    
    var description: String {
        let prefix = "Warning:"
        switch self {
        case let .DuplicateQuestion(question):
            return "\(prefix) Question with the phrase: '\(question)' is already defined."
        }
    }
}