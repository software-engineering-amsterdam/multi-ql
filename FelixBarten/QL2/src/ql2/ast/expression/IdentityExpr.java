package ql2.ast.expression;

import ql2.BaseVisitor;
import ql2.ast.Expr;

public class IdentityExpr extends Expr {

	private String ID;
	
	public IdentityExpr(String id) {
		// TODO Auto-generated constructor stub
		setID(id);
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return super.accept(visitor);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
