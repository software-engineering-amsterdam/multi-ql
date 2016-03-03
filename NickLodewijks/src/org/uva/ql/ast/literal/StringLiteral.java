package org.uva.ql.ast.literal;

import org.antlr.v4.runtime.ParserRuleContext;

public final class StringLiteral extends Literal {

	private final String value;

	public StringLiteral(ParserRuleContext context, String value) {
		super(context);
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(LiteralVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
