package eu.bankersen.kevin.ql.form.ast.expr.logic;

import eu.bankersen.kevin.ql.form.ast.expr.Expr;
import eu.bankersen.kevin.ql.form.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.UnaryExpr;

public class Not extends UnaryExpr {

    public Not(Expr expr, int line) {
	super(line, expr);
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }
}
