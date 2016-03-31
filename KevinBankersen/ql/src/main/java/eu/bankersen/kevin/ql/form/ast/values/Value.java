package eu.bankersen.kevin.ql.form.ast.values;

public abstract class Value {

	public abstract Object value();

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract int hashCode();

	public Value subtract(Value rhs) {
		return new EmptyValue();
	}

	public Value subtract(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value subtract(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value subtract(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value subtract(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value subtract(TextValue lhs) {
		return new EmptyValue();
	}

	public Value add(Value rhs) {
		return new EmptyValue();
	}

	public Value add(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value add(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value add(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value add(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value add(TextValue lhs) {
		return new EmptyValue();
	}

	public Value divide(Value rhs) {
		return new EmptyValue();
	}

	public Value divide(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value divide(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value divide(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value divide(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value divide(TextValue lhs) {
		return new EmptyValue();
	}

	public Value multiply(Value rhs) {
		return new EmptyValue();
	}

	public Value multiply(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value multiply(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value multiply(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value multiply(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value multiply(TextValue lhs) {
		return new EmptyValue();
	}

	public Value absolute() {
		return new EmptyValue();
	}

	public Value negate() {
		return new EmptyValue();
	}

	public Value or(Value rhs) {
		return new EmptyValue();
	}

	public Value or(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value or(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value or(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value or(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value or(TextValue lhs) {
		return new EmptyValue();
	}

	public Value and(Value rhs) {
		return new EmptyValue();
	}

	public Value and(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value and(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value and(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value and(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value and(TextValue lhs) {
		return new EmptyValue();
	}

	public Value equal(Value rhs) {
		return new EmptyValue();
	}

	public Value equal(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value equal(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value equal(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value equal(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value equal(TextValue lhs) {
		return new EmptyValue();
	}

	public Value greaterOrEqual(Value rhs) {
		return new EmptyValue();
	}

	public Value greaterOrEqual(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value greaterOrEqual(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value greaterOrEqual(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value greaterOrEqual(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value greaterOrEqual(TextValue lhs) {
		return new EmptyValue();
	}

	public Value greater(Value rhs) {
		return new EmptyValue();
	}

	public Value greater(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value greater(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value greater(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value greater(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value greater(TextValue lhs) {
		return new EmptyValue();
	}

	public Value lowerOrEqual(Value rhs) {
		return new EmptyValue();
	}

	public Value lowerOrEqual(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value lowerOrEqual(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value lowerOrEqual(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value lowerOrEqual(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value lowerOrEqual(TextValue lhs) {
		return new EmptyValue();
	}

	public Value lower(Value rhs) {
		return new EmptyValue();
	}

	public Value lower(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value lower(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value lower(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value lower(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value lower(TextValue lhs) {
		return new EmptyValue();
	}

	public Value notEqual(Value rhs) {
		return new EmptyValue();
	}

	public Value notEqual(EmptyValue lhs) {
		return new EmptyValue();
	}

	public Value notEqual(NumberValue lhs) {
		return new EmptyValue();
	}

	public Value notEqual(MoneyValue lhs) {
		return new EmptyValue();
	}

	public Value notEqual(BooleanValue lhs) {
		return new EmptyValue();
	}

	public Value notEqual(TextValue lhs) {
		return new EmptyValue();
	}

	public Value not() {
		return new EmptyValue();
	}

}
