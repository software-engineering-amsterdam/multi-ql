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
public class Mul extends BinaryExpr {

    public Mul(Expr lhs, Expr rhs) {
        super(lhs, rhs);
    }

}
