package eu.bankersen.kevin.ql.form.ast.values;

public class EmptyValue extends Value {

	@Override
	public Object value() {
		return null;
	}

	@Override
	public String toString() {
		return "Empty";
	}

	@Override
	public Value subtract(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value add(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value divide(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value multiply(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value absolute() {
		return new EmptyValue();
	}

	@Override
	public Value negate() {
		return new EmptyValue();
	}

	@Override
	public Value or(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value and(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value equal(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value greaterOrEqual(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value greater(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value lowerOrEqual(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value lower(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public Value notEqual(Value rhs) {
		return new EmptyValue();
	}

	public Value not(Value rhs) {
		return new EmptyValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EmptyValue)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 13;
	}
}
