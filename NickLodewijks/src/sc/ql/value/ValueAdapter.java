package sc.ql.value;

public abstract class ValueAdapter extends Value {

	@Override
	public Value add(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value add(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value add(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value subtract(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value subtract(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value subtract(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value mul(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value mul(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value mul(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value div(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value div(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value div(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue greaterThanOrEqual(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue greaterThanOrEqual(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue greaterThanOrEqual(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue lessThanOrEqual(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue lessThanOrEqual(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue lessThanOrEqual(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue greaterThan(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue greaterThan(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue greaterThan(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue lessThan(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue lessThan(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue lessThan(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue or(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue or(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue or(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue and(NumberValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue and(BooleanValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue and(StringValue other) {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public BooleanValue not() {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public NumberValue negative() {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public NumberValue positive() {
		throw new UnsupportedOperationException(getClass().getName());
	}

}
