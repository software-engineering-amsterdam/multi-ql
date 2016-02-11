package org.uva.sea.ql.ast;

public enum ValueType {
	BOOLEAN("boolean"), INTEGER("integer"), STRING("string"), MONEY("money");

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

		return null;
	}
}
