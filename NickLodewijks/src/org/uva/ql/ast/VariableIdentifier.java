package org.uva.ql.ast;

public class VariableIdentifier extends ASTNode {

	private final String name;

	// The declaration can be set during analysis, or set by the TypeChecker
	// during semantic analysis.
	private ValueType type;

	public VariableIdentifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
