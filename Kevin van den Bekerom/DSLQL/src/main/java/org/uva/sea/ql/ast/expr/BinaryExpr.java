package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public abstract class BinaryExpr extends Expr implements Visitable {
	protected Expr lhs, rhs;

	public Expr getLhs() {
		return lhs;
	}

	public Expr getRhs() {
		return rhs;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
