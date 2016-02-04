package org.uva.sea.ql.ast.expr;

public class BooleanLiteral extends BooleanExpr {

	private final boolean value;

	public BooleanLiteral(boolean value) {
		this.value = value;
	}

	@Override
	public Boolean interpret(Context context) {
		return value;
	}

}
