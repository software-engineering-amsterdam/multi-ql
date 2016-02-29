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
public class IntegerLiteral extends Literal {

    private final Integer value;

    public IntegerLiteral(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
