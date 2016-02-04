package org.uva.sea.ql.ast.expr;

public class StringVariable extends Variable {

	public StringVariable(String name) {
		super(name);
	}

	@Override
	public String interpret(Context context) {
		return (String) super.interpret(context);
	}
}
