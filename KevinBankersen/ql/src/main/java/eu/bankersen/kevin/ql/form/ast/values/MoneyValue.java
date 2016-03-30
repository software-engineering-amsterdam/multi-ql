package eu.bankersen.kevin.ql.form.ast.values;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyValue extends Value {

    private final BigDecimal value;

    public MoneyValue(String value) throws NumberFormatException {
	this.value = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }

    public MoneyValue(Integer value) {
	this.value = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }

    public MoneyValue(BigDecimal value) {
	this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
	return "€" + value.toString();
    }

    @Override
    public BigDecimal value() {
	return value;
    }

    // Operations
    @Override
    public Value subtract(Value rhs) {
	return rhs.subtract(this);
    }

    @Override
    public Value subtract(MoneyValue lhs) {
	return new MoneyValue(lhs.value().subtract(this.value));
    }

    @Override
    public Value add(Value rhs) {
	return rhs.add(this);
    }

    @Override
    public Value add(MoneyValue lhs) {
	return new MoneyValue(lhs.value().add(this.value));
    }

    @Override
    public Value add(StringValue lhs) {
	return new StringValue(lhs.value().concat(this.toString()));
    }

    @Override
    public Value absolute() {
	return new MoneyValue(this.value.abs());
    }

    @Override
    public Value negate() {
	return new MoneyValue(this.value.negate());
    }

    @Override
    public Value equal(MoneyValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) == 0 ? true : false);
    }

    @Override
    public Value greaterOrEqual(Value rhs) {
	return rhs.greaterOrEqual(this);
    }

    @Override
    public Value greaterOrEqual(MoneyValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) >= 0 ? true : false);
    }

    @Override
    public Value greater(Value rhs) {
	return rhs.greater(this);
    }

    @Override
    public Value greater(MoneyValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) > 0 ? true : false);
    }

    @Override
    public Value lowerOrEqual(Value rhs) {
	return rhs.lowerOrEqual(this);
    }

    @Override
    public Value lowerOrEqual(MoneyValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) <= 0 ? true : false);
    }

    @Override
    public Value lower(Value rhs) {
	return rhs.lower(this);
    }

    @Override
    public Value lower(MoneyValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) < 0 ? true : false);
    }

    @Override
    public Value notEqual(Value rhs) {
	return rhs.notEqual(this);
    }

    @Override
    public Value notEqual(MoneyValue lhs) {
	return new BooleanValue(lhs.value().compareTo(this.value) != 0 ? true : false);
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof MoneyValue)) {
	    return false;
	}

	return ((MoneyValue) obj).value.equals(this.value);
    }

    @Override
    public int hashCode() {
	return value.hashCode();
    }
}
