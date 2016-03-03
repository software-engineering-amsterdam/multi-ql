package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;

public class LessThan extends BinaryExpr {

	public LessThan(ParserRuleContext context, Expr lhs, Expr rhs) {
		super(context, lhs, rhs);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
