package eu.bankersen.kevin.ql.ast.var;

import eu.bankersen.kevin.ql.ast.expr.interpExpr;

public class IntegerVariable extends Variable{

	public IntegerVariable(String name, Type type) {
		super(name, type);
	}

	@Override
	public Integer result(interpExpr context) {
		return (Integer) super.result(context);
	}
}
