package ql.ast.value;

import ql.ast.visitor.Visitor;

public class StringValue extends Value{
	private final String value;
	
	public StringValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
