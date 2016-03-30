package eu.bankersen.kevin.ql.form.ast.values;

public class BooleanValue extends Value {

    private final Boolean value;

    public BooleanValue(String value) throws IllegalArgumentException {
	if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")) {
	    this.value = true;
	} else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no")) {
	    this.value = false;
	} else {
	    throw new IllegalArgumentException();
	}
    }

    public BooleanValue(Boolean value) {
	this.value = value;
    }

    @Override
    public Boolean value() {
	return value;
    }

    @Override
    public String toString() {
	return value.toString();
    }

    @Override
    public Value absolute() {
	return new BooleanValue(true);
    }

    @Override
    public Value negate() {
	return new BooleanValue(false);
    }

    @Override
    public Value or(Value rhs) {
	return rhs.or(this);
    }

    @Override
    public Value or(BooleanValue lhs) {
	return new BooleanValue(lhs.value() || this.value);
    }

    @Override
    public Value and(Value rhs) {
	return rhs.and(this);
    }

    @Override
    public Value and(BooleanValue lhs) {
	return new BooleanValue(lhs.value() && this.value);
    }

    @Override
    public Value equal(Value rhs) {
	return rhs.equal(this);
    }

    @Override
    public Value equal(BooleanValue lhs) {
	return new BooleanValue(lhs.value() == this.value);
    }

    @Override
    public Value notEqual(Value rhs) {
	return rhs.notEqual(this);
    }

    @Override
    public Value notEqual(BooleanValue lhs) {
	return new BooleanValue(lhs.value() != this.value);
    }

    @Override
    public Value not() {
	return new BooleanValue(!this.value);
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
