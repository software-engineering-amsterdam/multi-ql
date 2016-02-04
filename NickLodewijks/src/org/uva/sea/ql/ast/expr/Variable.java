package org.uva.sea.ql.ast.expr;

public class Variable extends Expr {

	private final String name;

	public Variable(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public Object interpret(Context context) {
		return context.getValue(name);
	}

}
