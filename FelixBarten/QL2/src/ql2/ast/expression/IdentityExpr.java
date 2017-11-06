package ql2.ast.expression;

import ql2.BaseVisitor;

public class IdentityExpr extends Expr {

	private String identifier;
	
	public IdentityExpr(String id) {
		this.identifier = id;
	}

	public String getID() {
		return identifier;
	}

	public void setID(String id) {
		this.identifier = id;
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
