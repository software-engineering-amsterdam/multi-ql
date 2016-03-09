package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public class GreaterThanOrEquals extends BinaryExpr {

	public GreaterThanOrEquals(ASTSourceInfo context, Expr lhs, Expr rhs) {
		super(context, lhs, rhs);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
