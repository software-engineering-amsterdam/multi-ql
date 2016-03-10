package ql.ast.expression;

import ql.ast.visitor.Visitor;

public class Neg extends Expression {
	private final Expression expression;

	public Neg(Expression expression) {
		super(expression.getLineNumber());
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
