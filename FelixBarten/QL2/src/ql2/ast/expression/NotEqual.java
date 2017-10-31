package ql2.ast.expression;

import ql2.BaseVisitor;
import ql2.ast.BinaryExpr;
import ql2.ast.Expr;

public class NotEqual extends BinaryExpr {

	public NotEqual(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
