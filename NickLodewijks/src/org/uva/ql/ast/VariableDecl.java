package org.uva.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class VariableDecl extends ASTNode {

	private final VariableType type;
	private final VariableIdentifier identifier;

	public VariableDecl(ParserRuleContext context, VariableType type, VariableIdentifier identifier) {
		super(context);

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
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
