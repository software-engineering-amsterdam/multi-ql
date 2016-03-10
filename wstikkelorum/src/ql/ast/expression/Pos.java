package ql.ast.expression;

import ql.ast.visitor.Visitor;

public class Pos extends Expression {
	private final Expression expression;

	public Pos(Expression expression) {
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
