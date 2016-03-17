package sc.ql.value;

public final class StringValue extends ValueAdapter {

	private final String value;

	public StringValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public StringValue parse(String text) {
		return new StringValue(text);
	}

	@Override
	public Value add(Value value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Value subtract(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Value mul(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Value div(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue greaterThanOrEqual(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue greaterThan(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue lessThanOrEqual(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue lessThan(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue equal(Value other) {
		return other.equal(this);
	}

	@Override
	public BooleanValue equal(NumberValue other) {
		return BooleanValue.FALSE;
	}

	@Override
	public BooleanValue equal(BooleanValue other) {
		return BooleanValue.FALSE;
	}

	@Override
	public BooleanValue equal(StringValue other) {
		return new BooleanValue(this.value.equals(other.value));
	}

	@Override
	public BooleanValue or(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue and(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StringValue)) {
			return false;
		}

		return ((StringValue) obj).value.equals(this.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}