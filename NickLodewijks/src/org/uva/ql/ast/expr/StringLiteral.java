package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;

public final class StringLiteral extends Expr {

	private final String value;

	public StringLiteral(ParserRuleContext context, String value) {
		super(context);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
