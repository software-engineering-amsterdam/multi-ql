package eu.bankersen.kevin.ql.form.ast.values;

public abstract class Value {

    public abstract Object value();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    public Value subtract(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value subtract(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value subtract(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value subtract(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value subtract(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value subtract(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value add(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value add(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value add(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value add(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value add(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value add(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value divide(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value divide(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value divide(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value divide(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value divide(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value divide(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value multiply(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value multiply(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value multiply(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value multiply(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value multiply(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value multiply(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value absolute() {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value negate() {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value or(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value or(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value or(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value or(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value or(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value or(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value and(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value and(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value and(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value and(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value and(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value and(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value equal(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value equal(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value equal(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value equal(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value equal(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value equal(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greaterOrEqual(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greaterOrEqual(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value greaterOrEqual(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greaterOrEqual(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greaterOrEqual(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greaterOrEqual(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greater(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greater(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value greater(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greater(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greater(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value greater(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lowerOrEqual(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lowerOrEqual(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value lowerOrEqual(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lowerOrEqual(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lowerOrEqual(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lowerOrEqual(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lower(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lower(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value lower(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lower(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lower(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value lower(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value notEqual(Value rhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value notEqual(EmptyValue lhs) {
	return new EmptyValue();
    }

    public Value notEqual(IntegerValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value notEqual(MoneyValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value notEqual(BooleanValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value notEqual(StringValue lhs) {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Value not() {
	throw new UnsupportedOperationException(getClass().getSimpleName());
    }

}
