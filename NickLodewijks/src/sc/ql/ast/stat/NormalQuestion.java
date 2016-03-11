package sc.ql.ast.stat;

import sc.ql.ast.type.ValueType;

public final class NormalQuestion extends Question {

	public NormalQuestion(ValueType type, String id, String label) {
		super(type, id, label);
	}

	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
