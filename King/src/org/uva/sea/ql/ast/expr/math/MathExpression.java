package org.uva.sea.ql.ast.expr.math;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.binary.BinaryExpression;

public abstract class MathExpression extends BinaryExpression {

	public MathExpression(Expr e1, Expr e2) {
		super(e1, e2);
	}

}
