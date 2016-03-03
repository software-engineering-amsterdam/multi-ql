package org.uva.sea.ql.evaluator;

public class BoolValue extends Value {
	
	private final boolean value;
	
	public BoolValue(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}
	
	@Override
	public Value not() {
		return new BoolValue(!getValue());
	}

}
