package ast.statement;

import ast.expression.Expression;
import ast.form.Body;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class IfStatement implements Visitable{
	private Expression expression;
	private Body body;
	
	public IfStatement(Expression result, Body result2) {
		this.expression = result;
		this.body = result2;
	}
	
	public Expression getExpression() {
		return expression;
	}

	public Body getBody() {
		return body;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
