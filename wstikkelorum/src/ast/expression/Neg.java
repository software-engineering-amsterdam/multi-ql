package ast.expression;

import ast.visitor.Visitor;

public class Neg extends Expression {
	private final Expression expression;

	public Neg(Expression result) {
		super(result.getLineNumber());
		this.expression = result;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
