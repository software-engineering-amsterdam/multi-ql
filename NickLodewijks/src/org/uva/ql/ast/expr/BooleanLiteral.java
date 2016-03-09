package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public final class BooleanLiteral extends Expr {

	private final boolean value;

	public BooleanLiteral(ASTSourceInfo context, boolean value) {
		super(context);

		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
