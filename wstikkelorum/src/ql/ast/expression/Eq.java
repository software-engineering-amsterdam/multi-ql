package ql.ast.expression;

import ql.ast.visitor.Visitor;

public class Eq extends BinaryExpression {
	public Eq(int lineNumber, Expression lhs, Expression rhs) {
		super(lineNumber, lhs, rhs);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
