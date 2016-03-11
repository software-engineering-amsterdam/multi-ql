package org.uva.qls.ast.literal;

import org.uva.ql.ast.value.BooleanValue;

public final class BooleanLiteral extends QLSLiteral {

	public static final BooleanLiteral TRUE = new BooleanLiteral(true);
	public static final BooleanLiteral FALSE = new BooleanLiteral(false);

	private final BooleanValue value;

	public BooleanLiteral(boolean value) {
		this.value = new BooleanValue(value);
	}

	public BooleanValue getValue() {
		return value;
	}
}
