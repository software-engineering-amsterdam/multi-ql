package ast.expression;

import ast.visitor.Visitor;

public class Neg extends Expression {
	private Expression expression;
	
	public Neg(Expression result) {
		this.expression = result;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
