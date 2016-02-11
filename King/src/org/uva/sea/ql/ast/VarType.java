package org.uva.sea.ql.ast;

public class VarType {
	private final String name;
	private final ValueType type;

	public VarType(String name) {
		this.name = name;
		this.type = ValueType.getByName(name);
	}

	public String getName() {
		return name;
	}

	public ValueType getType() {
		return type;
	}
}
