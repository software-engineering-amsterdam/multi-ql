package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;

public class Or extends BooleanExpr {

    public Or(final Expr lhs, final Expr rhs, final int line) {
	super.lhs = lhs;
	super.rhs = rhs;
	super.line = line;
    }

    @Override
    public final Boolean eval() {
	return (Boolean) lhs.eval() || (Boolean) rhs.eval();
    }

}
