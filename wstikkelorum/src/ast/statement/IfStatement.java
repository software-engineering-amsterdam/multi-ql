package ast.statement;

import ast.TreeNode;
import ast.expression.Expression;
import ast.form.Body;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class IfStatement extends TreeNode implements Visitable {
	private Expression expression;
	private Body body;

	public IfStatement(int lineNumber, Expression result, Body result2) {
		super(lineNumber);
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
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
