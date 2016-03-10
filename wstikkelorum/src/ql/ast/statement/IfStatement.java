package ql.ast.statement;

import ql.ast.TreeNode;
import ql.ast.expression.Expression;
import ql.ast.form.Body;
import ql.ast.visitor.Visitable;
import ql.ast.visitor.Visitor;

public class IfStatement extends TreeNode implements Visitable {
	private final Expression condition;
	private final Body body;

	public IfStatement(int lineNumber, Expression result, Body result2) {
		super(lineNumber);
		this.condition = result;
		this.body = result2;
	}

	public Expression getCondition() {
		return condition;
	}

	public Body getBody() {
		return body;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
