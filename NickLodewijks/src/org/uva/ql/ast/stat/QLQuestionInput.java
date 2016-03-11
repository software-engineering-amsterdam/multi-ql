package org.uva.ql.ast.stat;

import org.uva.ql.ast.type.QLType;

public final class QLQuestionInput extends QLQuestion {

	public QLQuestionInput(QLType type, String id, String label) {
		super(type, id, label);
	}

	@Override
	public <T, U> T accept(QLStatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
