package ql.ast.expression;

import ql.ast.visitor.Visitor;

public class BinaryExpression extends Expression {
	private final Expression lhs;
	private final Expression rhs;

	public BinaryExpression(int lineNumber, Expression lhs, Expression rhs) {
		super(lineNumber);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression getLhs() {
		return lhs;
	}

	public Expression getRhs() {
		return rhs;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
