package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNodeVisitor;

public abstract class AbstractUnaryExpr extends Expr {

	protected final Expr expr;

	public AbstractUnaryExpr(Expr expr) {
		this.expr = expr;
	}

	@Override
	public final void accept(ASTNodeVisitor visitor) {
		_accept(visitor);
		
		expr.accept(visitor);
	}

	protected abstract void _accept(ASTNodeVisitor visitor);
}
