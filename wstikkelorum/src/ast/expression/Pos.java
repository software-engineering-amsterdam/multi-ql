package ast.expression;

import ast.visitor.Visitor;

public class Pos extends Expression {
	private Expression expression;

	public Pos(Expression result) {
		super(result.getLineNumber());
		this.expression = result;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
