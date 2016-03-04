package org.uva.sea.ql.ast.expr.unary;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class NOT extends UnaryExpression {

	public NOT(Expr expr) {
		super(expr);
	}
	
	@Override
	public Type accept(QLNodeVisitor visitor) {
		return visitor.visit(this);
	}

}
