package eu.bankersen.kevin.ql.form.ast.statements;

import eu.bankersen.kevin.ql.form.ast.expressions.Expression;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitor;

public class ElseStatement extends IFStatement {

	private final Body elseBody;

	public ElseStatement(Expression expr, Body body, Body elseBody, int line) {
		super(expr, body, line);
		this.elseBody = elseBody;
	}

	public Body elseBody() {
		return elseBody;
	}

	@Override
	public <T> void accept(Visitor<T> visitor, T context) {
		visitor.visit(this, context);
	}
}
