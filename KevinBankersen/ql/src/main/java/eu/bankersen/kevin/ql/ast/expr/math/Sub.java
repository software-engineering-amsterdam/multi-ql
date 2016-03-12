package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.BinaryExpr;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class Sub extends BinaryExpr {

    public Sub(Expr lhs, Expr rhs, int line) {
	super(line, lhs, rhs);
    }

    @Override
    public QLValue eval(Environment context) {
	QLValue lhs = lhs().eval(context);
	QLValue rhs = rhs().eval(context);

	return lhs.subtract(rhs);
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }

}
