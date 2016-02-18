package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.expr.literal.Literal;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class LiteralExpression extends Expr {
	private Literal<?> literal;
	public LiteralExpression(Literal<?> literal) {
		this.literal = literal;
	}

	@Override
	public void accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub

	}
	
	public ValueType getLiteralType() {
		return literal.type();
	}

}
