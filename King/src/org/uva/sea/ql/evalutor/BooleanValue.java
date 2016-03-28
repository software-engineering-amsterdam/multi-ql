package org.uva.sea.ql.evalutor;

public class BooleanValue extends Value {

	private final boolean value;

	public BooleanValue(boolean value) {
		this.value = value;
	}

	@Override
	public Boolean getBooleanValue() {
		return this.value;
	}

	@Override
	public Value not() {
		return new BooleanValue(!this.value);
	}

	@Override
	public Value and(Value value) {
		return value.andBoolean(this);
	}

	@Override
	public Value andBoolean(BooleanValue bool) {
		return new BooleanValue(bool.value && this.value);
	}

	@Override
	public Value or(Value value) {
		return value.orBoolean(this);
	}

	@Override
	public Value orBoolean(BooleanValue bool) {
		return new BooleanValue(bool.value || this.value);
	}

	@Override
	public Value equal(Value value) {
		return value.equalBoolean(this);
	}

	@Override
	public Value equalBoolean(BooleanValue bool) {
		return new BooleanValue(bool.value == this.value);
	}

	@Override
	public String toString() {
		return "[ " + value + " ]";
	}

}
