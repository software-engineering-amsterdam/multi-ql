/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.question;

import ql.ast.expression.Ident;
import ql.ast.form.Label;
import ql.ast.type.QuestionType;

/**
 *
 * @author sander
 */
public class SimpleQuestion extends Question {

    public SimpleQuestion(Ident questionId, Label questionLabel, QuestionType questionType) {
        super(questionId, questionLabel, questionType);
    }
    
}
