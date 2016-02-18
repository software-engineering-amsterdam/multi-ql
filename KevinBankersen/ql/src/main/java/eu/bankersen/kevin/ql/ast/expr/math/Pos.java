package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.IntegerExpr;

public class Pos extends IntegerExpr {

    private final Expr expr;

    public Pos(final Expr expr, final int line) {
	this.expr = expr;
	super.line = line;
    }

    @Override
    public final Integer eval() {
	return Math.abs((Integer) expr.eval());
    }

}
