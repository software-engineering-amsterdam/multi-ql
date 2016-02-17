package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.IntegerExpr;

public class Mul extends IntegerExpr {

    public Mul(final Expr lhs, final Expr rhs) {
	super.lhs =  lhs;
	super.rhs =  rhs;
    }

    @Override
    public final Integer result() {
	return (Integer) lhs.result() * (Integer) rhs.result();
    }


}
