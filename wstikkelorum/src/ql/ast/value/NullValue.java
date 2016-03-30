package ql.ast.value;

import ql.ast.visitor.Visitor;

public class NullValue extends Value{
	
	public NullValue() {
	}

	@Override
	public Object getValue() {
		return null;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
