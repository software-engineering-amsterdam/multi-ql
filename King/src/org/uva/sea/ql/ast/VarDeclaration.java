package org.uva.sea.ql.ast;

public class VarDeclaration {
	private final VarType type;
	private final VarIdentifier identifier;

	public VarDeclaration(VarType type, VarIdentifier identifier) {
		this.identifier = identifier;
		this.type = type;
	}

	public VarIdentifier getIdentifier() {
		return identifier;
	}

	public VarType getType() {
		return type;
	}
}
