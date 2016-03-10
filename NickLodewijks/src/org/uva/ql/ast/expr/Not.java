package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public class Not extends UnaryExpr {

	public Not(ASTSourceInfo context, Expr expr) {
		super(context, expr);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
