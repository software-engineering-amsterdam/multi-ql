package ql2.ast.expression.logic;

import ql2.BaseVisitor;
import ql2.ast.expression.BinaryExpr;
import ql2.ast.expression.Expr;

public class Equal extends BinaryExpr  {

	public Equal(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
