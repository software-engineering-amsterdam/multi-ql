package ast.statement;

import ast.Visitable;
import ast.Visitor;
import ast.expression.Expression;
import ast.form.Body;

public class IfStatement implements Visitable{
	private Expression orExpression;
	private Body body;
	
	public IfStatement(Expression result, Body result2) {
		this.orExpression = result;
		this.body = result2;
	}
	
	public Expression getOrExpression() {
		return orExpression;
	}

	public Body getBody() {
		return body;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
