package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public class And extends BinaryExpr {

	public And(ASTSourceInfo sourceInfo, Expr lhs, Expr rhs) {
		super(sourceInfo, lhs, rhs);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
