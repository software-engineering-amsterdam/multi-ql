package org.uva.sea.ql.ast.expr.math;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class Sub extends MathExpression {

	public Sub(Expr e1, Expr e2) {
		super(e1, e2);
	}

	@Override
	public <T> T accept(QLNodeVisitor<T> visitor, boolean context) {
		return visitor.visit(this,context);
	}

}
