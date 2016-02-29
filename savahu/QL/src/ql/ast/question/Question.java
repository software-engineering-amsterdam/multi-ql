/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.question;

import ql.ast.ASTNode;
import ql.ast.expression.Ident;
import ql.ast.form.Label;
import ql.ast.type.QuestionType;

/**
 *
 * @author sander
 */
public class Question implements ASTNode {

    private final Ident _QuestionId;
    private final Label _QuestionLabel;
    private final QuestionType _QuestionType;

    public Question(Ident questionId, Label questionLabel, QuestionType questionType) {
        this._QuestionId = questionId;
        this._QuestionLabel = questionLabel;
        this._QuestionType = questionType;
    }

    public Ident getId() {
        return this._QuestionId;
    }

    public Label getLabel() {
        return this._QuestionLabel;
    }
    
    public QuestionType getType() {
        return this._QuestionType;
    }

}
