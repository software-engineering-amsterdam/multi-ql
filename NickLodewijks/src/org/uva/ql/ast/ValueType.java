package org.uva.ql.ast;

public enum ValueType {
	BOOLEAN("bool"), INTEGER("int"), STRING("str");

	private final String name;

	private ValueType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}