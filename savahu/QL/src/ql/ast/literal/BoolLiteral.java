/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.literal;

import ql.ast.expression.Expr;

/**
 *
 * @author sander
 */
public class BoolLiteral extends Literal {

    private final Boolean value;

    public BoolLiteral(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }

}
