package org.uva.ql.domain;

import org.uva.ql.QLInterpreterContext;
import org.uva.ql.QLInterpreter;
import org.uva.ql.ast.expr.Expr;

public class QuestionCondition {

	private final Expr condition;

	public QuestionCondition(Expr condition) {
		this.condition = condition;
	}

	public boolean evaluate(QLInterpreterContext context) {
		return QLInterpreter.interpret(condition, context);
	}
}
