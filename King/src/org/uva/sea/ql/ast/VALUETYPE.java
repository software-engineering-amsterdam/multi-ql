package org.uva.sea.ql.ast;

public enum VALUETYPE {
	BOOLEAN("boolean"), INTEGER("integer"), STRING("string"), MONEY("money");

	private final String name;

	private VALUETYPE(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static VALUETYPE getByName(String name) {
		for (VALUETYPE type : values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}

		return null;
	}
}
