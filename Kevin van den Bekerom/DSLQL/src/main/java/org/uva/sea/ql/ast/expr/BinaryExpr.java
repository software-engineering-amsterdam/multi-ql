package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public abstract class BinaryExpr extends Expr implements Visitable {
	protected Expr lhs, rhs;

	public Expr getLhs() {
		return lhs;
	}

	public Expr getRhs() {
		return rhs;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}

}
