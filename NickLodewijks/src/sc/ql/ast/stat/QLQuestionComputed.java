package sc.ql.ast.stat;

import sc.ql.ast.expr.Expression;
import sc.ql.ast.type.QLType;

public final class QLQuestionComputed extends QLQuestion {

	private final Expression computation;

	public QLQuestionComputed(QLType type, String id, String label, Expression computation) {
		super(type, id, label);

		this.computation = computation;
	}

	public Expression getComputation() {
		return computation;
	}

	@Override
	public <T, U> T accept(QLStatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

}
