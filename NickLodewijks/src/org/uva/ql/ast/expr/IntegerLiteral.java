package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;

public final class IntegerLiteral extends Expr {

	private final Integer value;

	public IntegerLiteral(ParserRuleContext context, Integer value) {
		super(context);
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
