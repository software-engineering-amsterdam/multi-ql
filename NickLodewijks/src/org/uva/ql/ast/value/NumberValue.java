package org.uva.ql.ast.value;

public final class NumberValue extends ValueAdapter {

	private final Integer value;

	public NumberValue(int value) {
		this.value = value;
	}

	@Override
	public Value add(Value value) {
		return value.add(this);
	}

	@Override
	public NumberValue add(NumberValue other) {
		return new NumberValue(this.value + other.value);
	}

	@Override
	public Value subtract(Value other) {
		return other.negative().add(this);
	}

	@Override
	public Value subtract(NumberValue other) {
		return new NumberValue(this.value - other.value);
	}

	@Override
	public Value mul(Value other) {
		return other.mul(this);
	}

	@Override
	public Value mul(NumberValue other) {
		return new NumberValue(this.value * other.value);
	}

	@Override
	public Value div(Value other) {
		return other.div(this);
	}

	@Override
	public Value div(NumberValue other) {
		return new NumberValue(other.value / this.value);
	}

	@Override
	public BooleanValue greaterThanOrEqual(Value other) {
		return other.lessThan(this).or(other.equal(this));
	}

	@Override
	public BooleanValue greaterThanOrEqual(NumberValue other) {
		return new BooleanValue(this.value >= other.value);
	}

	@Override
	public BooleanValue lessThanOrEqual(Value other) {
		return other.greaterThan(this).or(other.equal(this));
	}

	@Override
	public BooleanValue lessThanOrEqual(NumberValue other) {
		return new BooleanValue(this.value <= other.value);
	}

	@Override
	public BooleanValue greaterThan(Value other) {
		return other.lessThan(this).and(other.equal(this).not());
	}

	@Override
	public BooleanValue greaterThan(NumberValue other) {
		return new BooleanValue(this.value > other.value);
	}

	@Override
	public BooleanValue lessThan(Value other) {
		return other.greaterThan(this).and(other.equal(this).not());
	}

	@Override
	public BooleanValue lessThan(NumberValue other) {
		return new BooleanValue(this.value < other.value);
	}

	@Override
	public BooleanValue equal(Value other) {
		return other.equal(this);
	}

	@Override
	public BooleanValue equal(NumberValue other) {
		return new BooleanValue(this.value.equals(other.value));
	}

	@Override
	public BooleanValue equal(BooleanValue other) {
		return BooleanValue.FALSE;
	}

	@Override
	public BooleanValue equal(StringValue other) {
		return BooleanValue.FALSE;
	}

	@Override
	public BooleanValue and(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue or(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public NumberValue negative() {
		return new NumberValue(Math.negateExact(value));
	}

	@Override
	public NumberValue positive() {
		return new NumberValue(+value);
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NumberValue)) {
			return false;
		}

		return ((NumberValue) obj).value.equals(this.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}