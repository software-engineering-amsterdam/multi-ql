package ql.ast.expression;

import ql.BaseVisitor;
import ql.ast.Expr;

public class UnaryExpr extends Expr {
	private Expr expression;

	
	public UnaryExpr (Expr exp){

		this.expression = exp;

	}


	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
