package org.uva.ql.ast.literal;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.ExprVisitor;

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
