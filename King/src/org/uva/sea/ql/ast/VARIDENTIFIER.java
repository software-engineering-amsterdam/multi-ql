package org.uva.sea.ql.ast;

public class VARIDENTIFIER {
	private final String name;
	
	private VALUETYPE type;

	public VARIDENTIFIER(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public VALUETYPE getType() {
		return type;
	}

	public void setType(VALUETYPE type) {
		this.type = type;
	}
}
