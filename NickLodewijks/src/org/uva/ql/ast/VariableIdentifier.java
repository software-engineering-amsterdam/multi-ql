package org.uva.ql.ast;

public class VariableIdentifier extends ASTNode {

	private final String name;

	public VariableIdentifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
