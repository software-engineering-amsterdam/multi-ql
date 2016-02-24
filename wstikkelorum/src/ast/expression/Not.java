package ast.expression;

import ast.visitor.Visitor;

public class Not extends Expression {
	private Expression expression;

	public Not(Expression result) {
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
