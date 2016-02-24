//
//  ConcreteASTVisitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class ConcreteASTVisitor: ASTVisitor {
    
    func visit(form: Form) {
        form.block.accept(self)
    }
    func visit(block: Block) {
        block.statements.forEach {
            $0.accept(self)
        }
    }
    func visit(questionDeclaration: QuestionDeclaration) {
        questionDeclaration.computation?.accept(self)
    }
    func visit(ifStatement: IfStatement) {
        
    }
    func visit(elseIfStatement: ElseIfStatement) {
        
    }
    func visit(integerLiteral: IntegerLiteral) {
        
    }
    func visit(floatLiteral: FloatLiteral) {
        
    }
    func visit(booleanLiteral: BooleanLiteral) {
        
    }
    func visit(variable: Variable) {
        
    }
    func visit(addExpression: AddExpression) {
        debugPrint("Found an add expression!")
    }
    func visit(subExpression: SubExpression) {
        
    }
    func visit(mulExpression: MulExpression) {
        
    }
    func visit(divExpression: DivExpression) {
        
    }
    func visit(ltExpression: LtExpression) {
        
    }
    func visit(letExpression: LetExpression) {
        
    }
    func visit(gtExpression: GtExpression) {
        
    }
    func visit(getExpression: GetExpression) {
        
    }
    func visit(eqExpression: EqExpression) {
        
    }
    func visit(neqExpression: NeqExpression) {
        
    }
    func visit(andExpression: AndExpression) {
        
    }
    func visit(orExpression: OrExpression) {
        
    }
    func visit(notExpression: NotExpression) {
        
    }
    
}