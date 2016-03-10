package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public abstract class UnaryExpr extends Expr {

	private final Expr expr;

	public UnaryExpr(ASTSourceInfo context, Expr expr) {
		super(context);
		this.expr = expr;
	}

	public final Expr expr() {
		return expr;
	}
}
