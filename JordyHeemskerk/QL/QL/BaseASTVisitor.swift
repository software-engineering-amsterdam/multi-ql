//
//  Visitor.swift
//  QL
//
//  Created by Jordy Heemskerk on 24/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Foundation

class BaseASTVisitor: ASTVisitor {
    
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
        ifStatement.conditionClause.accept(self)
        ifStatement.block.accept(self)
        ifStatement.elseClause?.accept(self)
    }
    
    func visit(elseIfStatement: ElseIfStatement) {
        elseIfStatement.conditionClause?.accept(self)
        elseIfStatement.block.accept(self)
        elseIfStatement.elseClause?.accept(self)
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
        addExpression.lhs.accept(self)
        addExpression.rhs.accept(self)
    }
    
    func visit(subExpression: SubExpression) {
        subExpression.lhs.accept(self)
        subExpression.rhs.accept(self)
    }
    
    func visit(mulExpression: MulExpression) {
        mulExpression.lhs.accept(self)
        mulExpression.rhs.accept(self)
    }
    
    func visit(divExpression: DivExpression) {
        divExpression.lhs.accept(self)
        divExpression.rhs.accept(self)
    }
    
    func visit(ltExpression: LtExpression) {
        ltExpression.lhs.accept(self)
        ltExpression.rhs.accept(self)
    }
    
    func visit(letExpression: LetExpression) {
        letExpression.lhs.accept(self)
        letExpression.rhs.accept(self)
    }
    
    func visit(gtExpression: GtExpression) {
        gtExpression.lhs.accept(self)
        gtExpression.rhs.accept(self)
    }
    
    func visit(getExpression: GetExpression) {
        getExpression.lhs.accept(self)
        getExpression.rhs.accept(self)
    }
    
    func visit(eqExpression: EqExpression) {
        eqExpression.lhs.accept(self)
        eqExpression.rhs.accept(self)
    }
    
    func visit(neqExpression: NeqExpression) {
        neqExpression.lhs.accept(self)
        neqExpression.rhs.accept(self)
    }
    
    func visit(andExpression: AndExpression) {
        andExpression.lhs.accept(self)
        andExpression.rhs.accept(self)
    }
    
    func visit(orExpression: OrExpression) {
        orExpression.lhs.accept(self)
        orExpression.rhs.accept(self)
    }
    
    func visit(notExpression: NotExpression) {
        notExpression.operand.accept(self)
    }
    
}