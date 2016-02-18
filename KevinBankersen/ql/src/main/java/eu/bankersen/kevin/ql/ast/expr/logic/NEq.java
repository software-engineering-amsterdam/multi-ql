package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;

public class NEq extends BooleanExpr {

    public NEq(final Expr lhs, final Expr rhs, final int line) {
	super.lhs = lhs;
	super.rhs = rhs;
	super.line = line;
    }

    @Override
    public final Boolean eval() {
	return !lhs.eval().equals(rhs.eval());
    }

}
