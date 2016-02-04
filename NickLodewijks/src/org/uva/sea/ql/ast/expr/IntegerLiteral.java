package org.uva.sea.ql.ast.expr;

public class IntegerLiteral extends IntegerExpr {

	private final Integer value;

	public IntegerLiteral(Integer value) {
		this.value = value;
	}

	@Override
	public Integer interpret(Context context) {
		return value;
	}

}
