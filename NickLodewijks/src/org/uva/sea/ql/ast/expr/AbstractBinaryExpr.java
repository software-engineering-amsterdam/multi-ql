package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNodeVisitor;

public abstract class AbstractBinaryExpr extends Expr {

	protected final Expr lhs;
	protected final Expr rhs;

	public AbstractBinaryExpr(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expr left() {
		return lhs;
	}

	public Expr right() {
		return rhs;
	}

	@Override
	public final void accept(ASTNodeVisitor visitor) {
		_accept(visitor);

		lhs.accept(visitor);
		rhs.accept(visitor);
	}

	protected abstract void _accept(ASTNodeVisitor visitor);
}
