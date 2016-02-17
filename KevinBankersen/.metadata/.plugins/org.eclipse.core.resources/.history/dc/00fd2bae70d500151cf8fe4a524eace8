package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.IntegerExpr;

public class Pos extends IntegerExpr {

    private final Expr expr;

    public Pos(final Expr expr) {
	this.expr = expr;
    }

    @Override
    public final Integer result() {
	return Math.abs((Integer) expr.result());
    }

}
