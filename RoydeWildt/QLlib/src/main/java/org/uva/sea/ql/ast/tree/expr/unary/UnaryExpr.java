package org.uva.sea.ql.ast.tree.expr.unary;

import org.uva.sea.ql.ast.tree.expr.Expr;

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class UnaryExpr extends Expr {

    public UnaryExpr(int line) {
        super(line);
    }

    public abstract <T> T getValue();

    @Override
    public String toString() {
        return this.getSymbol() + this.getValue().toString();
    }
}
