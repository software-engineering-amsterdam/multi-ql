package org.uva.ql.ast.literal;

import org.antlr.v4.runtime.ParserRuleContext;

public final class IntegerLiteral extends Literal {

	private final Integer value;

	public IntegerLiteral(ParserRuleContext context, Integer value) {
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
