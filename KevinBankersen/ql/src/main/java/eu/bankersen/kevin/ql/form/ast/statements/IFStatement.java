package eu.bankersen.kevin.ql.form.ast.statements;

import eu.bankersen.kevin.ql.form.ast.Body;
import eu.bankersen.kevin.ql.form.ast.expressions.Expression;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitor;

public class IFStatement extends Statement {

	private final Expression condition;
	private final Body body;

	public IFStatement(Expression condition, Body body, int line) {
		super(line);
		this.body = body;
		this.condition = condition;
	}

	public Expression condition() {
		return condition;
	}

	public Body body() {
		return body;
	}

	@Override
	public <T> void accept(Visitor<T> visitor, T context) {
		visitor.visit(this, context);
	}
}
