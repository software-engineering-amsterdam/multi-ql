package org.uva.sea.ql.ast;

public class VarIdentifier {
	private final String name;
	
	private ValueType type;

	public VarIdentifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}
}
