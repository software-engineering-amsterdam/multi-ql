package eu.bankersen.kevin.ql.form.ast.expr.math;

import eu.bankersen.kevin.ql.form.ast.expr.Expr;
import eu.bankersen.kevin.ql.form.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.UnaryExpr;

public class Pos extends UnaryExpr {

    public Pos(final Expr expr, final int line) {
	super(line, expr);
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }

}
