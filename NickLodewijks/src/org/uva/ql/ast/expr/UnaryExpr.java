package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class UnaryExpr extends Expr {

	protected final Expr expr;

	public UnaryExpr(ParserRuleContext context, Expr expr) {
		super(context);
		this.expr = expr;
	}

	public final Expr getExpr() {
		return expr;
	}
}
