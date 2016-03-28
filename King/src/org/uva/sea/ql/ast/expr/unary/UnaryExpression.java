package org.uva.sea.ql.ast.expr.unary;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public abstract class UnaryExpression extends Expr {
	protected final Expr expr;

	public UnaryExpression(Expr expr) {
		this.expr = expr;
	}

	public Expr getExpression() {
		return expr;
	}

	@Override
	public String toString() {
		return "[ " + expr.toString() + " ]";
	}

}
