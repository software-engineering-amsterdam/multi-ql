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
public abstract class Literal extends Expr {

    private Boolean isLiteral = true;

    public void setLiteral() {
        this.isLiteral = true;
    }

    public Boolean isLiteral() {
        return this.isLiteral;
    }

}
