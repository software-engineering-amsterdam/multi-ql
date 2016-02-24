package org.uva.ql.domain;

import org.uva.ql.ast.QLExpressionInterpreter;
import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Expr;

public class QLQuestionCondition {

	private final Expr condition;

	public QLQuestionCondition(Expr condition) {
		this.condition = condition;
	}

	public boolean evaluate(Context context) {
		return QLExpressionInterpreter.interpret(condition, context);
	}
}
