package org.uva.sea.ql.ast.literal;

import org.uva.sea.ql.ast.ValueType;

public class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(boolean value) {
		super(ValueType.BOOLEAN, value);
	}
}
