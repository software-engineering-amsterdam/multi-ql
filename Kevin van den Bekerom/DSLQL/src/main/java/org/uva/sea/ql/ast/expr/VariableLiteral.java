package org.uva.sea.ql.ast.expr;

public class VariableLiteral extends Expr {
	String identifier;
	Object value;
	
	public VariableLiteral(String identifier) {
		this.identifier = identifier;
		this.value = null;
	}

	@Override
	Object eval() throws ClassCastException {
		return value;
	}
}
