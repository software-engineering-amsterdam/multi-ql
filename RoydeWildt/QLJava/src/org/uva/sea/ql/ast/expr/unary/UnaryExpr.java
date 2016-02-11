package org.uva.sea.ql.ast.expr.unary;

import org.uva.sea.ql.ast.expr.Expr;

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class UnaryExpr extends Expr {
    private Expr val;

    public UnaryExpr(int line, Expr val) {
        super(line);
        this.val = val;
    }

    public Expr getVal() {
        return val;
    }
}
