package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;

public class Negative extends UnaryExpr {

	public Negative(ParserRuleContext context, Expr expr) {
		super(context, expr);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
