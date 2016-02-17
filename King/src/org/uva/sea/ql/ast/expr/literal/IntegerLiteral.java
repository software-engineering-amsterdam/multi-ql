package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.ValueType;

public class IntegerLiteral extends Literal<Integer> {

	public IntegerLiteral(Integer value) {
		super(ValueType.INTEGER, value);
	}

}
