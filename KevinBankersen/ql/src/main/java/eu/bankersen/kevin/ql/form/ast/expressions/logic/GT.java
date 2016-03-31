package eu.bankersen.kevin.ql.form.ast.expressions.logic;

import eu.bankersen.kevin.ql.form.ast.expressions.Binary;
import eu.bankersen.kevin.ql.form.ast.expressions.Expression;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.Visitor;

public class GT extends Binary {

	public GT(final Expression lhs, final Expression rhs, final int line) {
		super(line, lhs, rhs);
	}

	@Override
	public <T, U> T accept(Visitor<T, U> v, U context) {
		return v.visit(this, context);
	}

}
