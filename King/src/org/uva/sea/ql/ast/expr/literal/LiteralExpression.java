package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class LiteralExpression extends Expr {
	private Literal<?> literal;
	public LiteralExpression(Literal<?> literal) {
		this.literal = literal;
	}

	@Override
	public Type accept(QLNodeVisitor visitor) {
		return visitor.visit(this);

	}
	
	public Type getLiteralType() {
		return literal.getType();
	}
	
	public Literal<?> getLiteral() {
		return literal;
	}

}
