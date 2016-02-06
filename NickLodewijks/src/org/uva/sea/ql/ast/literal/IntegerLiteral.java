package org.uva.sea.ql.ast.literal;

import org.uva.sea.ql.ast.ValueType;

public class IntegerLiteral extends Literal<Integer> {

	public IntegerLiteral(Integer value) {
		super(ValueType.INTEGER, value);
	}
}
