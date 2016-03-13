//
//  SemanticWarnings.swift
//  QL
//
//  Created by Jordy Heemskerk on 25/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

enum SemanticWarning: SemanticLoggable {
    
    case DuplicateQuestion(question: String, position: Position)
    
    var description: String {
        return "Warning: \(self.warningDescription)"
    }
    
    private var warningDescription: String {
        switch self {
        case let .DuplicateQuestion(question, position):
            return "Question with the phrase: '\(question)' is already defined. [\(position.line):\(position.character)]"
        }
    }
}