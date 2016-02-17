package org.uva.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class VariableType extends ASTNode {

	private final String name;
	private final ValueType type;

	public VariableType(ParserRuleContext context, String name) {
		super(context);
		this.name = name;
		this.type = ValueType.getByName(name);
	}

	public String getName() {
		return name;
	}

	public ValueType getType() {
		return type;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
