package org.uva.sea.ql.ast.expr.unary;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class NOT extends UnaryExpression {

	public NOT(Expr expr) {
		super(expr);
	}

	@Override
	public <T> T accept(QLNodeVisitor<T> visitor, boolean context) {
		return visitor.visit(this,context);
	}

}
