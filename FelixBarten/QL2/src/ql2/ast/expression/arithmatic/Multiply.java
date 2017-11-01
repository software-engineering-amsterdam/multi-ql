package ql2.ast.expression.arithmatic;

import ql2.BaseVisitor;
import ql2.ast.expression.BinaryExpr;
import ql2.ast.expression.Expr;

public class Multiply extends BinaryExpr {

	public Multiply(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
