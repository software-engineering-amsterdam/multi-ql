package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class VarDeclaration extends ASTNode{
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

	@Override
	public void accept(QLNodeVisitor visitor) {
		visitor.visit(this);
		
		type.accept(visitor);
		identifier.accept(visitor);
		
	}
}
