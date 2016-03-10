package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public class Positive extends UnaryExpr {

	public Positive(ASTSourceInfo context, Expr expr) {
		super(context, expr);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
