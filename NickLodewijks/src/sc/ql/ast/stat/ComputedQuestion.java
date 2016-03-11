package sc.ql.ast.stat;

import sc.ql.ast.Expression;
import sc.ql.ast.type.ValueType;

public final class ComputedQuestion extends Question {

	private final Expression computation;

	public ComputedQuestion(ValueType type, String id, String label, Expression computation) {
		super(type, id, label);

		this.computation = computation;
	}

	public Expression getComputation() {
		return computation;
	}

	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

}
