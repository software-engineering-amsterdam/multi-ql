package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.BinaryExpr;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.ast.object.value.QLValue;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class Mul extends BinaryExpr {

    public Mul(final Expr lhs, final Expr rhs, final int line) {
	super(line, lhs, rhs);
    }

    @Override
    public final QLValue eval(Environment context) {
	QLValue lhs = lhs().eval(context);
	QLValue rhs = rhs().eval(context);

	return lhs.multiply(lhs);
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }

}
