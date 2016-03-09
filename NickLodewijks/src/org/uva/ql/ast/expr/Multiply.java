package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public class Multiply extends BinaryExpr {

	public Multiply(ASTSourceInfo context, Expr lhs, Expr rhs) {
		super(context, lhs, rhs);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
