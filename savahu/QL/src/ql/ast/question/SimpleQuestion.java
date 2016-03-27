/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.question;

import ql.ast.IVisitor;
import ql.ast.expression.Ident;
import ql.ast.type.Type;

/**
 *
 * @author sander
 */
public class SimpleQuestion extends Question {

    public SimpleQuestion(Ident questionId, Label questionLabel, Type questionType) {
        super(questionId, questionLabel, questionType);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visitChildren(IVisitor visitor) {
        this.getId().accept(visitor);
        this.getLabel().accept(visitor);
        this.getType().accept(visitor);

    }
}
