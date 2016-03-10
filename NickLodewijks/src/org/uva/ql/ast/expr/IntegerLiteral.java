package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;
import org.uva.ql.ast.NumberValue;

public final class IntegerLiteral extends Expr {

	private final NumberValue value;

	public IntegerLiteral(ASTSourceInfo context, Integer value) {
		super(context);
		this.value = new NumberValue(value);
	}

	public NumberValue getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
