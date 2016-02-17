package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.ValueType;

public class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(boolean value) {
		super(ValueType.BOOLEAN, value);
		// TODO Auto-generated constructor stub
	}

}
