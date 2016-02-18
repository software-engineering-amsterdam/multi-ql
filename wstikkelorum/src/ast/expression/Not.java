package ast.expression;

import ast.visitor.Visitor;

public class Not extends Expression {
	private Expression expression;
	
	public Not(Expression result) {
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
