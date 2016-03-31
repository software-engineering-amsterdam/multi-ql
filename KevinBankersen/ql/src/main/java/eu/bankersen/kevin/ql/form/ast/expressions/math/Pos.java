package eu.bankersen.kevin.ql.form.ast.expressions.math;

import eu.bankersen.kevin.ql.form.ast.expressions.Expression;
import eu.bankersen.kevin.ql.form.ast.expressions.Unary;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.Visitor;

public class Pos extends Unary {

	public Pos(final Expression expr, final int line) {
		super(line, expr);
	}

	@Override
	public <T, U> T accept(Visitor<T, U> v, U context) {
		return v.visit(this, context);
	}

}
