package org.uva.ql.ast.stat;

import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.type.QLType;

public final class QLQuestionComputed extends QLQuestion {

	private final Expr computation;

	public QLQuestionComputed(QLType type, String id, String label, Expr computation) {
		super(type, id, label);

		this.computation = computation;
	}

	public Expr getComputation() {
		return computation;
	}

	@Override
	public <T, U> T accept(QLStatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

}
