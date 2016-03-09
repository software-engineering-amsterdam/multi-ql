package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public final class StringLiteral extends Expr {

	private final String value;

	public StringLiteral(ASTSourceInfo context, String value) {
		super(context);
		this.value = value.replaceAll("\"", "");
	}

	public String getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
