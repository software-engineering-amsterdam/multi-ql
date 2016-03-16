package sc.qls.ast.literal;

import sc.ql.value.StringValue;

public final class StringLiteral extends Literal {

	private final StringValue value;

	public StringLiteral(String value) {
		this.value = new StringValue(value.replaceAll("\"", ""));
	}

	public StringValue getValue() {
		return value;
	}
}
