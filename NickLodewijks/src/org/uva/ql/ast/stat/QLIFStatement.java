package org.uva.ql.ast.stat;

import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.QLBlock;

public final class QLIFStatement extends QLStatement {

	private final Expr condition;
	private final QLBlock body;

	public QLIFStatement(Expr condition, QLBlock body) {
		this.condition = condition;
		this.body = body;
	}

	public Expr getCondition() {
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
