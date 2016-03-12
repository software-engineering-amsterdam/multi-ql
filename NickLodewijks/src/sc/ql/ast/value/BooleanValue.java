package sc.ql.ast.value;

public final class BooleanValue extends ValueAdapter {

	public static final BooleanValue FALSE = new BooleanValue(false);
	public static final BooleanValue TRUE = new BooleanValue(true);

	private final Boolean value;

	public BooleanValue(String text) {
		if (text.equalsIgnoreCase("yes") || text.equals("true")) {
			value = true;
		} else if (text.equalsIgnoreCase("no") || text.equals("false")) {
			value = false;
		} else {
			throw new IllegalArgumentException(text);
		}
	}

	public BooleanValue(boolean value) {
		this.value = value;
	}

	@Override
	public BooleanValue parse(String text) {
		return new BooleanValue(text);
	}

	@Override
	public BooleanValue equal(Value other) {
		return other.equal(this);
	}

	@Override
	public BooleanValue equal(NumberValue value) {
		return BooleanValue.FALSE;
	}

	@Override
	public BooleanValue equal(BooleanValue other) {
		return new BooleanValue(this.value.equals(other.value));
	}

	@Override
	public BooleanValue equal(StringValue other) {
		return BooleanValue.FALSE;
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
	public BooleanValue lessThanOrEqual(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue greaterThan(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue lessThan(Value other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BooleanValue or(Value other) {
		return other.or(this);
	}

	@Override
	public BooleanValue or(BooleanValue other) {
		return new BooleanValue(this.value || other.value);
	}

	@Override
	public BooleanValue and(Value other) {
		return other.and(this);
	}

	@Override
	public BooleanValue and(BooleanValue other) {
		return new BooleanValue(this.value && other.value);
	}

	@Override
	public BooleanValue not() {
		return new BooleanValue(!this.value);
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BooleanValue)) {
			return false;
		}

		return ((BooleanValue) obj).value.equals(this.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}