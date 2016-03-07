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
	
	@Override
	public Value and(Value value) {
		return value.andBool(this);
	}
	
	@Override
	public Value andBool(BoolValue value) {
		return new BoolValue(value.getValue() && this.getValue());
	}
	
	@Override
	public Value or(Value value) {
		return value.orBool(this);
	}
	
	@Override
	public Value orBool(BoolValue value) {
		return new BoolValue(value.getValue() || this.getValue());
	}

}