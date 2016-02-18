package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class Add extends MathExpression {

	public Add(Expr e1, Expr e2) {
		super(e1, e2);
	}

	@Override
	public void accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub
		super.accept(visitor);
	}

	
}
