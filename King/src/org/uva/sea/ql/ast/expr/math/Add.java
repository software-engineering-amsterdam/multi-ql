package org.uva.sea.ql.ast.expr.math;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class Add extends MathExpression {

	public Add(Expr e1, Expr e2) {
		super(e1, e2);
	}

	@Override
	public <T> T accept(QLNodeVisitor<T> visitor, boolean context) {
		return visitor.visit(this,context);
	}

}
