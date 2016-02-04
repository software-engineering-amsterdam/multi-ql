package org.uva.sea.ql.ast.expr;

public class StringLiteral extends StringExpr {

	private final String value;

	public StringLiteral(String value) {
		this.value = value;
	}

	@Override
	public String interpret(Context context) {
		return value;
	}

}
