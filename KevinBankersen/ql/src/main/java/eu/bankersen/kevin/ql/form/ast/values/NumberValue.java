package eu.bankersen.kevin.ql.form.ast.values;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberValue extends Value {

	private final BigDecimal value;

	public NumberValue(String value) throws NumberFormatException {
		this.value = new BigDecimal(value).setScale(0, RoundingMode.HALF_DOWN);
	}

	public NumberValue(Integer value) {
		this.value = new BigDecimal(value).setScale(0, RoundingMode.HALF_DOWN);
	}

	public NumberValue(BigDecimal value) {
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

	@Override
	public Value subtract(Value rhs) {
		return rhs.subtract(this);
	}

	@Override
	public Value subtract(NumberValue lhs) {
		return new NumberValue(lhs.value().subtract(this.value));
	}

	@Override
	public Value add(Value rhs) {
		return rhs.add(this);
	}

	@Override
	public Value add(NumberValue lhs) {
		return new NumberValue(lhs.value().add(this.value));
	}

	@Override
	public Value add(TextValue lhs) {
		return new TextValue(lhs.value().concat(this.value.toString()));
	}

	@Override
	public Value divide(Value rhs) {
		return rhs.divide(this);
	}

	@Override
	public Value divide(NumberValue lhs) {
		return new NumberValue(lhs.value().divide(this.value));
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
	public Value multiply(NumberValue lhs) {
		return new NumberValue(lhs.value().multiply(this.value));
	}

	@Override
	public Value multiply(MoneyValue lhs) {
		return new MoneyValue(lhs.value().multiply(this.value));
	}

	@Override
	public Value absolute() {
		return new NumberValue(this.value.abs());
	}

	@Override
	public Value negate() {
		return new NumberValue(this.value.negate());
	}

	@Override
	public Value equal(Value rhs) {
		return rhs.equal(this);
	}

	@Override
	public Value equal(NumberValue lhs) {
		return new BooleanValue(lhs.value().compareTo(this.value) == 0 ? true : false);
	}

	@Override
	public Value greaterOrEqual(Value rhs) {
		return rhs.greaterOrEqual(this);
	}

	@Override
	public Value greaterOrEqual(NumberValue lhs) {
		return new BooleanValue(lhs.value().compareTo(this.value) >= 0 ? true : false);
	}

	@Override
	public Value greater(Value rhs) {
		return rhs.greater(this);
	}

	@Override
	public Value greater(NumberValue lhs) {
		return new BooleanValue(lhs.value().compareTo(this.value) > 0 ? true : false);
	}

	@Override
	public Value lowerOrEqual(Value rhs) {
		return rhs.lowerOrEqual(this);
	}

	@Override
	public Value lowerOrEqual(NumberValue lhs) {
		return new BooleanValue(lhs.value().compareTo(this.value) <= 0 ? true : false);
	}

	@Override
	public Value lower(Value rhs) {
		return rhs.lower(this);
	}

	@Override
	public Value lower(NumberValue lhs) {
		return new BooleanValue(lhs.value().compareTo(this.value) < 0 ? true : false);
	}

	@Override
	public Value notEqual(Value rhs) {
		return rhs.notEqual(this);
	}

	@Override
	public Value notEqual(NumberValue lhs) {
		return new BooleanValue(lhs.value().compareTo(this.value) != 0 ? true : false);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NumberValue)) {
			return false;
		}

		return ((NumberValue) obj).value.equals(this.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
