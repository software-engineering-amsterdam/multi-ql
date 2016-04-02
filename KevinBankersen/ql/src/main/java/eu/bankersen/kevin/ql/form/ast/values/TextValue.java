package eu.bankersen.kevin.ql.form.ast.values;

public class TextValue extends Value {

	private final String value;

	public TextValue(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	// Operations
	@Override
	public Value add(Value rhs) {
		return rhs.add(this);
	}

	@Override
	public Value add(TextValue lhs) {
		return new TextValue(lhs.value.concat(this.value));
	}

	@Override
	public Value equal(Value rhs) {
		return rhs.equal(this);
	}

	@Override
	public Value equal(TextValue lhs) {
		return new BooleanValue(lhs.value().length() == this.value.length());
	}

	@Override
	public Value notEqual(Value rhs) {
		return rhs.notEqual(this);
	}

	@Override
	public Value notEqual(TextValue lhs) {
		return new BooleanValue(!lhs.value().equals(this.value));
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TextValue)) {
			return false;
		}

		return ((TextValue) obj).value.equals(this.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
