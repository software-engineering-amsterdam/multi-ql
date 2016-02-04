package org.uva.sea.ql.ast.expr;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Add extends Expr{
    Expr lhs;
    Expr rhs;

    public Add(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "Add(" + lhs.toString() + ", " + rhs.toString() + ")";
    }
}
