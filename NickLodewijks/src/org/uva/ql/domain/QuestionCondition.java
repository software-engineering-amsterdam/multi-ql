package org.uva.ql.domain;

import org.uva.ql.QLInterpreter;
import org.uva.ql.QLInterpreterContext;
import org.uva.ql.ast.expr.Expr;

public final class QuestionCondition {

	private final Expr condition;

	public QuestionCondition(Expr condition) {
		this.condition = condition;
	}

	public boolean evaluate(QLInterpreterContext context) {
		return QLInterpreter.interpret(condition, context);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuestionCondition)) {
			return false;
		}

		return condition.equals(((QuestionCondition) obj).condition);
	}

	@Override
	public int hashCode() {
		return condition.hashCode();
	}
}
