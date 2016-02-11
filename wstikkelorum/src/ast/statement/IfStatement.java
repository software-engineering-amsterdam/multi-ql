package ast.statement;

import ast.expression.Expression;
import ast.form.Body;

public class IfStatement {
	private Expression orExpression;
	private Body body;
	public IfStatement(Expression result, Body result2) {
		this.orExpression = result;
		this.body = result2;
	}
}
