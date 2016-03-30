package eu.bankersen.kevin.ql.form.ast.values;

public class StringValue extends Value {

    private final String value;

    public StringValue(String value) {
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
    public Value add(StringValue lhs) {
	return new StringValue(lhs.value.concat(this.value));
    }

    @Override
    public Value and(Value rhs) {
	return rhs.and(this);
    }

    @Override
    public Value and(StringValue lhs) {
	return new BooleanValue(lhs.value().equals(this.value));
    }

    @Override
    public Value equal(Value rhs) {
	return rhs.equal(this);
    }

    @Override
    public Value equal(StringValue lhs) {
	return new BooleanValue(lhs.value().length() == this.value.length());
    }

    @Override
    public Value greaterOrEqual(Value rhs) {
	return rhs.greaterOrEqual(this);
    }

    @Override
    public Value greaterOrEqual(StringValue lhs) {
	return new BooleanValue(lhs.value().length() >= this.value.length());
    }

    @Override
    public Value greater(Value rhs) {
	return rhs.greater(this);
    }

    @Override
    public Value greater(StringValue lhs) {
	return new BooleanValue(lhs.value().length() > this.value.length());
    }

    @Override
    public Value lowerOrEqual(Value rhs) {
	return rhs.lowerOrEqual(this);
    }

    @Override
    public Value lowerOrEqual(StringValue lhs) {
	return new BooleanValue(lhs.value().length() <= this.value.length());
    }

    @Override
    public Value lower(Value rhs) {
	return rhs.lower(this);
    }

    @Override
    public Value lower(StringValue lhs) {
	return new BooleanValue(lhs.value().length() < this.value.length());
    }

    @Override
    public Value notEqual(Value rhs) {
	return rhs.notEqual(this);
    }

    @Override
    public Value notEqual(StringValue lhs) {
	return new BooleanValue(!lhs.value().equals(this.value));
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
