package eu.bankersen.kevin.ql.form.ast.values;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IntegerValue extends Value {

    private final BigDecimal value;

    public IntegerValue(String value) throws NumberFormatException {
	this.value = new BigDecimal(value).setScale(0, RoundingMode.HALF_DOWN);
    }

    public IntegerValue(Integer value) {
	this.value = new BigDecimal(value).setScale(0, RoundingMode.HALF_DOWN);
    }

    public IntegerValue(BigDecimal value) {
	this.value = value.setScale(0, RoundingMode.HALF_DOWN);
    }

    @Override
    public BigDecimal value() {
	return value;
    }

    @Override
    public String toString() {
	return value.toString();
    }

    // Operations
    @Override
    public Value subtract(Value rhs) {
	return rhs.subtract(this);
    }

    @Override
    public Value subtract(IntegerValue lhs) {
	return new IntegerValue(lhs.value().subtract(this.value));
    }

    @Override
    public Value add(Value rhs) {
	return rhs.add(this);
    }

    @Override
    public Value add(IntegerValue lhs) {
	return new IntegerValue(lhs.value().add(this.value));
    }

    @Override
    public Value add(StringValue lhs) {
	return new StringValue(lhs.value().concat(this.value.toString()));
    }

    @Override
    public Value divide(Value rhs) {
	return rhs.divide(this);
    }

    @Override
    public Value divide(IntegerValue lhs) {
	return new IntegerValue(lhs.value().divide(this.value));
    }

    @Override
    public Value divide(MoneyValue lhs) {
	return new MoneyValue(lhs.value().divide(this.value));
    }

    @Override
    public Value multiply(Value rhs) {
	return rhs.multiply(this);
    }

    @Override
    public Value multiply(IntegerValue lhs) {
	return new IntegerValue(lhs.value().multiply(this.value));
    }

    @Override
    public Value multiply(MoneyValue lhs) {
	return new MoneyValue(lhs.value().multiply(this.value));
    }

    @Override
    public Value absolute() {
	return new IntegerValue(this.value.abs());
    }

    @Override
    public Value negate() {
	return new IntegerValue(this.value.negate());
    }

    @Override
    public Value equal(Value rhs) {
	return rhs.equal(this);
    }

    @Override
    public Value equal(IntegerValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) == 0 ? true : false);
    }

    @Override
    public Value greaterOrEqual(Value rhs) {
	return rhs.greaterOrEqual(this);
    }

    @Override
    public Value greaterOrEqual(IntegerValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) >= 0 ? true : false);
    }

    @Override
    public Value greater(Value rhs) {
	return rhs.greater(this);
    }

    @Override
    public Value greater(IntegerValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) > 0 ? true : false);
    }

    @Override
    public Value lowerOrEqual(Value rhs) {
	return rhs.lowerOrEqual(this);
    }

    @Override
    public Value lowerOrEqual(IntegerValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) <= 0 ? true : false);
    }

    @Override
    public Value lower(Value rhs) {
	return rhs.lower(this);
    }

    @Override
    public Value lower(IntegerValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) < 0 ? true : false);
    }

    @Override
    public Value notEqual(Value rhs) {
	return rhs.notEqual(this);
    }

    @Override
    public Value notEqual(IntegerValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) != 0 ? true : false);
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof IntegerValue)) {
	    return false;
	}

	return ((IntegerValue) obj).value.equals(this.value);
    }

    @Override
    public int hashCode() {
	return value.hashCode();
    }
}
