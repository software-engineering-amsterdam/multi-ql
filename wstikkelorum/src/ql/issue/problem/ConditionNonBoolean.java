package ql.issue.problem;

import ql.ast.expression.Expression;
import ql.ast.type.ValueType;

public class ConditionNonBoolean extends Problem {
	public ConditionNonBoolean(Expression expression, ValueType expectedType, ValueType actualType) {
		super.errorMessage = String
				.format("Invalid type for condition. Expected Type: %s. Actual type: %s. On line number: %d",
						expectedType, actualType, expression.getLineNumber());
	}
}
