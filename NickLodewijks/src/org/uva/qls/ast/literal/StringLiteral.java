package org.uva.qls.ast.literal;

import org.uva.ql.ast.value.StringValue;

public final class StringLiteral extends QLSLiteral {

	private final StringValue value;

	public StringLiteral(String value) {
		this.value = new StringValue(value.replaceAll("\"", ""));
	}

	public StringValue getValue() {
		return value;
	}
}
