package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.BooleanType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class BooleanLiteral extends Expr {
	private final boolean value;
	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	public Boolean getValue() {
		return value;
	}
	
	public BooleanType getType() {
		return new BooleanType();
	}
	@Override
	public <T> T accept(QLNodeVisitor<T> visitor) {
		return visitor.visit(this);

	}

}
