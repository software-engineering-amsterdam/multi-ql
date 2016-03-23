package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.ast.expr.UnaryExpr;

public class Neg extends UnaryExpr {

    public Neg(final Expr expr, final int line) {
	super(line, expr);
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }
}
