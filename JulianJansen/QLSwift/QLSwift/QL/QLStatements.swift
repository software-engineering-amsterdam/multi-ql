//
//  QLStatements.swift
//  QLSwift
//
//  Created by Julian Jansen on 08-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol QLStatement: TypeVisitable, FormBuilderVisitable {}

class QLForm: QLStatement {
    let formName: String
    let codeBlock: [QLStatement]
    
    init(formName: String, codeBlock: [QLStatement]) {
        self.formName = formName
        self.codeBlock = codeBlock
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: FormBuilderVisitor) {
        return visitor.visit(self)
    }
}

class QLQuestion: QLStatement {
    let name: String
    let label: String
    let type: QLType
    let expression: QLExpression?
    
    init(name: String, label: String, type: QLType) {
        self.name = name
        self.label = label
        self.type = type
        self.expression = nil
    }
    
    init(name: String, label: String, type: QLType, expression: QLExpression) {
        self.name = name
        self.label = label
        self.type = type
        self.expression = expression
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: FormBuilderVisitor) {
        return visitor.visit(self)
    }
}

class QLIfStatement: QLStatement {
    let condition: QLExpression
    let codeBlock: [QLStatement]
    
    init(condition: QLExpression, codeBlock: [QLStatement]) {
        self.condition = condition
        self.codeBlock = codeBlock
    }
    
    func accept(visitor: TypeVisitor) -> QLType {
        return visitor.visit(self)
    }
    
    func accept(visitor: FormBuilderVisitor) {
        return visitor.visit(self)
    }
}