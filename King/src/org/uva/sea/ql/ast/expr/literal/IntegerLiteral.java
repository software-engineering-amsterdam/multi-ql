package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.IntegerType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class IntegerLiteral extends Expr {

	private final Integer value;

	public IntegerLiteral(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public IntegerType getType() {
		return new IntegerType();
	}

	@Override
	public <T> T accept(QLNodeVisitor<T> visitor) {
		return visitor.visit(this);

	}

}
