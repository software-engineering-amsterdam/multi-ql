package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.IntegerExpr;

public class Neg extends IntegerExpr {


    public Neg(final Expr expr) {
	super.rhs =  expr;
    }

    @Override
    public final Integer result() {
	return -(Integer) rhs.result();
    }

}
