package org.uva.sea.ql.ast.expr;

public class BooleanVariable extends Variable {

	public BooleanVariable(String name) {
		super(name);
	}

	@Override
	public Boolean interpret(Context context) {
		return (Boolean) super.interpret(context);
	}
}
