package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;

public class Neg extends Expr {

	private final Expr expr;

	public Neg(Expr expr) {
		this.expr = expr;
	}

	public Expr getExpr() {
		return expr;
	}

	@Override
	public Integer interpret(Context context) {
		return -Math.abs((Integer) expr.interpret(context));
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
