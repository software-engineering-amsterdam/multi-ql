package org.uva.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class VariableIdentifier extends ASTNode {

	private final String name;

	public VariableIdentifier(ParserRuleContext context, String name) {
		super(context);
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
