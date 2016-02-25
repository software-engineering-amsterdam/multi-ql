package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

public class TwoSidedExpression extends Expr {
	protected final Expr e1;
	protected final Expr e2;

	public TwoSidedExpression(Expr e1, Expr e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	public Expr getFirstExpression() {
		return e1;
	}

	public Expr getSecondExpression() {
		return e2;
	}

	@Override
	public void accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
