package org.uva.ql.ast.literal;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.ExprVisitor;

public final class BooleanLiteral extends Expr {

	private final boolean value;

	public BooleanLiteral(ParserRuleContext context, boolean value) {
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
