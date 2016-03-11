package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.ast.expr.UnaryExpr;
import eu.bankersen.kevin.ql.ast.object.value.QLValue;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class Not extends UnaryExpr {

    public Not(Expr expr, int line) {
	super(line, expr);
    }

    @Override
    public final QLValue eval(Environment context) {
	return expr().eval(context).not();
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }
}
