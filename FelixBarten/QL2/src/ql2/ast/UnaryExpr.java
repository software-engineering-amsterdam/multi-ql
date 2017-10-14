package ql2.ast;

import ql2.BaseVisitor;

public abstract class UnaryExpr extends Expr {
	
	private Expr expr;

	public UnaryExpr(Expr result) {
		this.expr = result;
	}
	
	public Expr getExpr() {
		return expr;
	}
	public void setExpr(Expr exp) {
		this.expr = exp;
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}
