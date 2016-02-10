package org.uva.ql.ast.literal;

import org.uva.ql.ast.ValueType;

public class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(boolean value) {
		super(ValueType.BOOLEAN, value);
	}
}
