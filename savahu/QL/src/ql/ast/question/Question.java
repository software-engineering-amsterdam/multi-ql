/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.question;

import ql.ast.expression.Ident;

/**
 *
 * @author sander
 */
public class Question {

    private final Ident questionName;
    private final String questionValue;

    public Question(Ident identifier, String questionValue) {
        this.questionName = identifier;
        this.questionValue = questionValue;
    }

    public Ident getQuestionName() {
        return this.questionName;
    }
    
    public String getQuestionValue() {
        return this.questionValue;
    }

}
