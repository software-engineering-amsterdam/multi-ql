package org.uva.sea.ql.ast;

public class VARTYPE {
	private final String name;
	private final VALUETYPE type;

	public VARTYPE(String name) {
		this.name = name;
		this.type = VALUETYPE.getByName(name);
	}

	public String getName() {
		return name;
	}

	public VALUETYPE getType() {
		return type;
	}
}
