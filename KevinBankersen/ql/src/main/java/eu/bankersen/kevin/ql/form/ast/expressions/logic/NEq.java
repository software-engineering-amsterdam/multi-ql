package eu.bankersen.kevin.ql.form.ast.expressions.logic;

import eu.bankersen.kevin.ql.form.ast.expressions.Binary;
import eu.bankersen.kevin.ql.form.ast.expressions.Expression;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.Visitor;

public class NEq extends Binary {

	public NEq(Expression lhs, Expression rhs, int line) {
		super(line, lhs, rhs);
	}

	@Override
	public <T, U> T accept(Visitor<T, U> v, U context) {
		return v.visit(this, context);
	}
}
