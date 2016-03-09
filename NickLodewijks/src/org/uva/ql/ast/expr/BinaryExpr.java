package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public abstract class BinaryExpr extends Expr {

	private final Expr lhs;
	private final Expr rhs;

	public BinaryExpr(ASTSourceInfo sourceInfo, Expr lhs, Expr rhs) {
		super(sourceInfo);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public final Expr left() {
		return lhs;
	}

	public final Expr right() {
		return rhs;
	}
}
