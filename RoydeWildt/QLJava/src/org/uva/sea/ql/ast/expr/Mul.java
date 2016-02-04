package org.uva.sea.ql.ast.expr;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Mul extends Expr {
    Expr lhs;
    Expr rhs;

    public Mul(Expr lhs, Expr rhs){
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "Mul(" + lhs.toString() + ", " + rhs.toString() + ")";
    }
}
