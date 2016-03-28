package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.type.Type;

public class VarDeclaration {
	private final Type type;
	private final VarIdentifier identifier;

	public VarDeclaration(Type type, VarIdentifier identifier) {
		this.identifier = identifier;
		this.type = type;
	}

	public VarIdentifier getIdentifier() {
		return identifier;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "[ " + identifier.toString() + " ]";
	}

}
