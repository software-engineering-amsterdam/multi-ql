/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.statement;

import ql.ast.expression.Ident;

/**
 *
 * @author sander
 */
public class Question extends Statement {

    private final Ident _QuestionName;
    private final String _QuestionValue;

    public Question() {
        this._QuestionName = null;
        this._QuestionValue = null;
    }

    public Ident getQuestionName() {
        return this._QuestionName;
    }

    public String getQuestionValue() {
        return this._QuestionValue;
    }

}
