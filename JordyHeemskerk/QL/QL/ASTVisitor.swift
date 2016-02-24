//
//  Visitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

protocol ASTVisitor {
    
    func visit(form: Form)
    func visit(block: Block)
    func visit(questionDeclaration: QuestionDeclaration)
    func visit(ifStatement: IfStatement)
    func visit(elseIfStatement: ElseIfStatement)
    func visit(integerLiteral: IntegerLiteral)
    func visit(floatLiteral: FloatLiteral)
    func visit(booleanLiteral: BooleanLiteral)
    func visit(variable: Variable)
    func visit(addExpression: AddExpression)
    func visit(subExpression: SubExpression)
    func visit(mulExpression: MulExpression)
    func visit(divExpression: DivExpression)
    func visit(ltExpression: LtExpression)
    func visit(letExpression: LetExpression)
    func visit(gtExpression: GtExpression)
    func visit(getExpression: GetExpression)
    func visit(eqExpression: EqExpression)
    func visit(neqExpression: NeqExpression)
    func visit(andExpression: AndExpression)
    func visit(orExpression: OrExpression)
    func visit(notExpression: NotExpression)
    
}