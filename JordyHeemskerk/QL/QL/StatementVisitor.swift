//
//  FormVisitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 02/03/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

protocol StatementVisitor {
    typealias StatementReturnType
    func visit(questionDeclaration: QuestionDeclaration) -> StatementReturnType
    func visit(ifStatement: IfStatement) -> StatementReturnType
    func visit(elseIfStatement: ElseIfStatement) -> StatementReturnType
}