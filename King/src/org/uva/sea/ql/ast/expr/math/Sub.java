package org.uva.sea.ql.ast.expr.math;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class Sub extends MathExpression {

	public Sub(Expr e1, Expr e2) {
		super(e1, e2);
	}
	
	@Override
	public Type accept(QLNodeVisitor visitor) {
		return visitor.visit(this);
	}
	
}
