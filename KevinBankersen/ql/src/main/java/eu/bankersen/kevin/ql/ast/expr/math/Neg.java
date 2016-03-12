package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.ast.expr.UnaryExpr;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class Neg extends UnaryExpr {

    public Neg(final Expr expr, final int line) {
	super(line, expr);
    }

    @Override
    public final QLValue eval(Environment context) {
	return expr().eval(context).negate();
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }
}
