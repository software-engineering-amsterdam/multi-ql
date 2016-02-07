package org.uva.sea.ql.ast;

public class VARDECLARATION {
	private final VARTYPE type;
	private final VARIDENTIFIER identifier;

	public VARDECLARATION(VARTYPE type, VARIDENTIFIER identifier) {
		this.identifier = identifier;
		this.type = type;
	}

	public VARIDENTIFIER getIdentifier() {
		return identifier;
	}

	public VARTYPE getType() {
		return type;
	}
}
