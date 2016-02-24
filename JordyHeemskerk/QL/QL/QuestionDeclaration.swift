//
//  QuestionDeclaration.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

struct QuestionDeclaration: Statement {
    
    let question: String
    let identifier: String
    let type: Type
    let computation: Expression?
    
    init(tupleInput: (((String, String), Type), [Expression])) {
        let (((question, identifier), type), computation) = tupleInput
        self.question = question
        self.identifier = identifier
        self.type = type
        self.computation = computation.first
    }
    
    func accept(visitor: ASTVisitor) {
        visitor.visit(self)
    }
}