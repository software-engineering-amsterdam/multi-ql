package org.uva.sea.ql.ast.expr;

public class Identifier extends Expr {

	private final String name;

	public Identifier(String name) {
		this.name = name;
	}

	@Override
	public Object interpret(Context context) {
		return context.getValue(name);
	}

}
