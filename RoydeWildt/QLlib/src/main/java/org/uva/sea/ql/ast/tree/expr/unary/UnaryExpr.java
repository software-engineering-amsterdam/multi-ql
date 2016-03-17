package org.uva.sea.ql.ast.tree.expr.unary;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.expr.Expr;

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class UnaryExpr extends Expr {

    public UnaryExpr(Token token) {
        super(token);
    }

    public abstract <T> T getValue();

    @Override
    public String toString() {
        return this.getSymbol() + this.getValue().toString();
    }
}
