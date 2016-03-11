package sc.ql.ast.stat;

import sc.ql.ast.expr.Expression;
import sc.ql.ast.form.QLBlock;

public final class QLIFStatement extends QLStatement {

	private final Expression condition;
	private final QLBlock body;

	public QLIFStatement(Expression condition, QLBlock body) {
		this.condition = condition;
		this.body = body;
	}

	public Expression getCondition() {
		return condition;
	}

	public QLBlock getBody() {
		return body;
	}

	@Override
	public <T, U> T accept(QLStatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
