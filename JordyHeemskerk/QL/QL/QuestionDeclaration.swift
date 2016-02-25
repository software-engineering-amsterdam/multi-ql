//
//  QuestionDeclaration.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation
import SwiftParsec

struct QuestionDeclaration: Statement {
    
    let question: String
    let identifier: String
    let type: Type
    let computation: Expression?
    let position: Position
    
    init(tupleInput: ((((String, String), Type), [Expression]), Position)) {
        let ((((question, identifier), type), computation), currentPosition) = tupleInput
        self.question = question
        self.identifier = identifier
        self.type = type
        self.computation = computation.first
        self.position = currentPosition
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
}