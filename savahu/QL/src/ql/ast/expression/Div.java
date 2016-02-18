/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.expression;

/**
 *
 * @author sander
 */
public class Div extends Expr {

    private final Expr lhs, rhs;

    public Div(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
