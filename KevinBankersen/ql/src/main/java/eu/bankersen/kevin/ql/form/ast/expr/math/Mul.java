package eu.bankersen.kevin.ql.form.ast.expr.math;

import eu.bankersen.kevin.ql.form.ast.expr.BinaryExpr;
import eu.bankersen.kevin.ql.form.ast.expr.Expr;
import eu.bankersen.kevin.ql.form.ast.expr.ExprVisitor;

public class Mul extends BinaryExpr {

    public Mul(final Expr lhs, final Expr rhs, final int line) {
	super(line, lhs, rhs);
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }

}
