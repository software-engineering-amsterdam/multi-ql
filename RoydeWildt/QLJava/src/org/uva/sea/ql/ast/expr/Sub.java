package org.uva.sea.ql.ast.expr;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Sub extends Expr {
    Expr lhs;
    Expr rhs;

    public Sub(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "Sub(" + lhs.toString() + ", " + rhs.toString() + ")";
    }
}
