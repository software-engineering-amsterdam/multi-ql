package org.uva.ql.ast;

public class VariableDecl extends ASTNode {

	private final VariableType type;
	private final VariableIdentifier identifier;

	public VariableDecl(VariableType type, VariableIdentifier identifier) {
		this.identifier = identifier;
		this.type = type;
	}

	public VariableIdentifier getId() {
		return identifier;
	}

	public VariableType getType() {
		return type;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context){
		return visitor.visit(this, context);
	}
}
