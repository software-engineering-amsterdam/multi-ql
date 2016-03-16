package sc.qls.ast.literal;

import sc.ql.value.BooleanValue;

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
