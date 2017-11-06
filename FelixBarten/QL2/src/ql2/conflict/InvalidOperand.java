package ql2.conflict;

import ql2.ast.expression.Expr;

public class InvalidOperand  extends Conflict {
	
	private Expr expression;
	
	public InvalidOperand(Expr e) {
		this.expression = e;
	}

}
