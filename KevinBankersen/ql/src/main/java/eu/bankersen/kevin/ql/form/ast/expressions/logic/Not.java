package eu.bankersen.kevin.ql.form.ast.expressions.logic;

import eu.bankersen.kevin.ql.form.ast.expressions.Expression;
import eu.bankersen.kevin.ql.form.ast.expressions.Unary;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.Visitor;

public class Not extends Unary {

	public Not(Expression expr, int line) {
		super(line, expr);
	}

	@Override
	public <T, U> T accept(Visitor<T, U> v, U context) {
		return v.visit(this, context);
	}
}
