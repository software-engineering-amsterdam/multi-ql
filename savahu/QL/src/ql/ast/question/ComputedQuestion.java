/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.question;

import ql.ast.expression.Expr;
import ql.ast.expression.Ident;
import ql.ast.form.Label;
import ql.ast.type.QuestionType;

/**
 *
 * @author sander
 */
public class ComputedQuestion extends Question {

    private Expr _Expression;

    public ComputedQuestion(Ident questionId, Label questionLabel, QuestionType questionType) {
        super(questionId, questionLabel, questionType);
    }

    public ComputedQuestion(Ident questionId, Label questionLabel, QuestionType questionType, Expr expression) {
        super(questionId, questionLabel, questionType);
        this._Expression = expression;
    }

    public Expr GetExpression() {
        return this._Expression;
    }
}
