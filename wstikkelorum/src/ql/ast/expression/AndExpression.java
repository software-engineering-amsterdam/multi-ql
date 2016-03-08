package ql.ast.expression;

import ql.ast.visitor.Visitor;

public class AndExpression extends BinaryExpression {
	public AndExpression(int lineNumber, Expression lhs, Expression rhs) {
		super(lineNumber, lhs, rhs);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
