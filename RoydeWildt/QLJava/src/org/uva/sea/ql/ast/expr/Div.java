package org.uva.sea.ql.ast.expr;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Div extends Expr {
    Expr lhs;
    Expr rhs;

    public Div(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "Mul(" + lhs.toString() + ", " + rhs.toString() + ")";
    }
}
