package org.uva.sea.ql.ast.tree.expr.binary;

import org.uva.sea.ql.ast.tree.expr.Expr;

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class BinaryExpr extends Expr {
    private Expr lhs;
    private Expr rhs;

    public BinaryExpr(int line, Expr lhs, Expr rhs) {
        super(line);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expr getLhs() {
        return lhs;
    }

    public Expr getRhs() {
        return rhs;
    }
}
