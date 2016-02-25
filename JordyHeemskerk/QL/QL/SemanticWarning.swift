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
        let prefix = "Warning:"
        switch self {
        case let .DuplicateQuestion(question, position):
            return "\(prefix) Question with the phrase: '\(question)' is already defined. [\(position.line):\(position.character)]"
        }
    }
}