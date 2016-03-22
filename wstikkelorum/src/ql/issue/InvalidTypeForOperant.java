package ql.issue;

import ql.ast.expression.Expression;
import ql.ast.types.ValueType;

public class InvalidTypeForOperant extends Issue {
	public InvalidTypeForOperant(Expression expression, ValueType expectedType, ValueType actualType) {
		super.errorMessage = String
				.format("Invalid type for operant. Expected Type: %s. Actual type: %s. On line number: %d",
						expectedType, actualType, expression.getLineNumber());
	}

}
