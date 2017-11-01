package ql2.ast.expression;

import ql2.BaseVisitor;

public class IdentityExpr extends Expr {

	private String ID;
	
	public IdentityExpr(String id) {
		ID = id;
	}

	public String getID() {
		return ID;
	}

	public void setID(String identifier) {
		ID = identifier;
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
