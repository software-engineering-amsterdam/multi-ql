/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.question;

import ql.ast.ASTNode;
import ql.ast.expression.Ident;
import ql.ast.type.Type;

/**
 *
 * @author sander
 */
public abstract class Question extends ASTNode {

    private final Ident _QuestionId;
    private final Label _QuestionLabel;
    private final Type _QuestionType;

    public Question(Ident questionId, Label questionLabel, Type questionType) {
        this._QuestionId = questionId;
        this._QuestionLabel = questionLabel;
        this._QuestionType = questionType;
        setType();
    }

    public void setType() {
        if (_QuestionType.isCompatibleToBoolean()) {
            _QuestionId.setBoolType();
        } else if (_QuestionType.isCompatibleToInteger()) {
            _QuestionId.setIntType();
        } else if (_QuestionType.isCompatibleToString()) {
            _QuestionId.setStringType();
        }

    }

    public Ident getId() {
        return this._QuestionId;
    }

    public Label getLabel() {
        return this._QuestionLabel;
    }

    public Type getType() {
        return this._QuestionType;
    }
}
