package ql2.ast.expression;

import ql2.BaseVisitor;
import ql2.ast.BinaryExpr;
import ql2.ast.Expr;

public class AndExpr extends BinaryExpr  {

	public AndExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
}
	
	

}
