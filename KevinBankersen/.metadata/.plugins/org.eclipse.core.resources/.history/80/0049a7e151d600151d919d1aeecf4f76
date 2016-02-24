package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.IntegerExpr;

public class Div extends IntegerExpr {

    public Div(final Expr lhs, final Expr rhs,  final int line) {
	super.lhs =  lhs;
	super.rhs =  rhs;
	super.line = line;
    }

    @Override
    public final Integer eval() {
	return (Integer) lhs.eval() / (Integer) rhs.eval();
    }

}
