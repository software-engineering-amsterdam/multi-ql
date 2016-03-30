package eu.bankersen.kevin.ql.form.ast.expr.math;

import eu.bankersen.kevin.ql.form.ast.expr.BinaryExpr;
import eu.bankersen.kevin.ql.form.ast.expr.Expr;
import eu.bankersen.kevin.ql.form.ast.expr.ExprVisitor;

public class Sub extends BinaryExpr {

    public Sub(Expr lhs, Expr rhs, int line) {
	super(line, lhs, rhs);
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }

}
