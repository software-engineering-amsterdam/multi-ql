package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;
import org.uva.ql.ast.value.StringValue;

public final class StringLiteral extends Expr {

	private final StringValue value;

	public StringLiteral(ASTSourceInfo context, String value) {
		super(context);
		this.value = new StringValue(value.replaceAll("\"", ""));
	}

	public StringValue getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
