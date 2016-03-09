package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;
import org.uva.ql.ast.value.BooleanValue;

public final class BooleanLiteral extends Expr {

	public static final BooleanLiteral TRUE = new BooleanLiteral(true);
	public static final BooleanLiteral FALSE = new BooleanLiteral(false);

	private final BooleanValue value;

	public BooleanLiteral(boolean value) {
		this(null, value);
	}

	public BooleanLiteral(ASTSourceInfo context, boolean value) {
		super(context);

		this.value = new BooleanValue(value);
	}

	public BooleanValue getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
