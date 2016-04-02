package ql.ast.statement;

import ql.ast.ASTNode;
import ql.ast.expression.Expression;
import ql.ast.form.Body;
import ql.ast.visitor.Visitable;
import ql.ast.visitor.Visitor;

public class IfStatement extends ASTNode implements Visitable {
	private final Expression condition;
	private final Body body;

	public IfStatement(int lineNumber, Expression expression, Body body) {
		super(lineNumber);
		this.condition = expression;
		this.body = body;
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
