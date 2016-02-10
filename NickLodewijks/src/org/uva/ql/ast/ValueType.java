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

	public static ValueType getByName(String name) {
		for (ValueType type : values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}

		assert false : "Unknown ValueType '" + name + "'";

		return null;
	}
}