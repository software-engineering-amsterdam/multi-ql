package ql.issue.problem;

import ql.ast.expression.Expression;
import ql.ast.type.ValueType;

public class InvalidTypeForOperand extends Problem {
	public InvalidTypeForOperand(Expression expression, ValueType expectedType, ValueType actualType) {
		super.errorMessage = String
				.format("Invalid type for operant. Expected Type: %s. Actual type: %s. On line number: %d",
						expectedType, actualType, expression.getLineNumber());
	}
}
