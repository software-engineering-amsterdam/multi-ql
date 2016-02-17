package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.TaxForm.interfaces.QLNodeVisitor;

public class OneSidedExpression extends Expr {
	protected final Expr expr;

	
	public OneSidedExpression(Expr expr) {
		this.expr = expr;
	}

	public Expr getExpression() {
		return expr;
	}

	@Override
	public void accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
