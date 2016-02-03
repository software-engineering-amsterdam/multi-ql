package org.uva.sea.ql.ast.expr;

public class IntegerVariable extends Variable {

	public IntegerVariable(String name) {
		super(name);
	}

	@Override
	public Integer interpret(Context context) {
		return (Integer) super.interpret(context);
	}
}
