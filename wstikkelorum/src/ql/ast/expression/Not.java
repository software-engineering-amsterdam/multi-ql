package ql.ast.expression;

import ql.ast.visitor.Visitor;

public class Not extends Expression {
	private final Expression expression;

	public Not(Expression expression) {
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
