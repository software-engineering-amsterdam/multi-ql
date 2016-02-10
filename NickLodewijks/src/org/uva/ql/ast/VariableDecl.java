package org.uva.ql.ast;

public class VariableDecl extends ASTNode {

	private final VariableType type;
	private final VariableIdentifier identifier;

	public VariableDecl(VariableType type, VariableIdentifier identifier) {
		this.identifier = identifier;
		this.type = type;
	}

	public VariableIdentifier getIdentifier() {
		return identifier;
	}

	public VariableType getType() {
		return type;
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);

		type.accept(visitor);
		identifier.accept(visitor);
	}

	@Override
	public Result validate() {
		return Result.TRUE();
	}
}
