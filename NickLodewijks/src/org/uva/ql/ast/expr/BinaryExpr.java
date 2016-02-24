package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class BinaryExpr extends Expr {

	private final Expr lhs;
	private final Expr rhs;

	public BinaryExpr(ParserRuleContext context, Expr lhs, Expr rhs) {
		super(context);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expr left() {
		return lhs;
	}

	public Expr right() {
		return rhs;
	}
}
