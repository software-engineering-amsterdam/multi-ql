package org.uva.sea.ql.ast.expr;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Expr {
    private Expr lhs;
    private Expr rhs;

    public Expr(){}

    //for unary expressions
    public Expr(Expr lhs) {
        this.lhs = lhs;
    }

    //for binary expressions
    public Expr(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        if(rhs == null)
            return this.getClass().getSimpleName() + "(" + lhs.toString() + ")";
        else
            return this.getClass().getSimpleName() + "(" + lhs.toString() + ", " + rhs.toString() + ")";
    }
}
