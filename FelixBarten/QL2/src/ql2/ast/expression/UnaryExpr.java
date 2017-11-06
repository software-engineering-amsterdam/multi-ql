package ql2.ast.expression;

import ql2.BaseVisitor;

public abstract class UnaryExpr extends Expr {
	
	private Expr expression;

	public UnaryExpr(Expr result) {
		this.expression = result;
	}
	
	public Expr getExpr() {
		return expression;
	}
	public void setExpr(Expr exp) {
		this.expression = exp;
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
