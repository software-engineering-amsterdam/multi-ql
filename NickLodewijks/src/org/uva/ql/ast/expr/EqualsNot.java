package org.uva.ql.ast.expr;

public class EqualsNot extends BinaryExpr {

	public EqualsNot(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
