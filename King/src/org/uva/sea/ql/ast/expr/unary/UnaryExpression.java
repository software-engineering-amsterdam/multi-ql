package org.uva.sea.ql.ast.expr.unary;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class UnaryExpression extends Expr {
	protected final Expr expr;

	
	public UnaryExpression(Expr expr) {
		this.expr = expr;
	}

	public Expr getExpression() {
		return expr;
	}

	@Override
	public Type accept(QLNodeVisitor visitor) {
		return null;
		
	}

}
