package nl.nicasso.ql.gui.evaluator.values;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyValue extends Value {

	private final BigDecimal value;

	public MoneyValue(BigDecimal value) {
		this.value = value.setScale(2, RoundingMode.HALF_UP);
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof MoneyValue)) {
			return false;
		}
		MoneyValue value = (MoneyValue) ob;
		return this.value.equals(value.getValue());
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public Value addition(Value arg) {
		return new MoneyValue(value.add((BigDecimal) arg.getValue()));
	}

	@Override
	public Value subtraction(Value arg) {
		return new MoneyValue(value.subtract((BigDecimal) arg.getValue()));
	}

	@Override
	public Value equal(Value arg) {
		return new BooleanValue(value.equals((BigDecimal) arg.getValue()));
	}

	@Override
	public Value notEqual(Value arg) {
		return new BooleanValue(!value.equals((BigDecimal) arg.getValue()));
	}

	@Override
	public Value division(Value arg) {
		return arg.divisionToMoney(this);
	}

	@Override
	public Value multiplication(Value arg) {
		return arg.multiplicationToMoney(this);
	}

	@Override
	public Value greater(Value arg) {
		return new BooleanValue(value.compareTo((BigDecimal) arg.getValue()) > 0);
	}

	@Override
	public Value greaterEqual(Value arg) {
		return new BooleanValue(value.compareTo((BigDecimal) arg.getValue()) >= 0);
	}

	@Override
	public Value less(Value arg) {
		return new BooleanValue(value.compareTo((BigDecimal) arg.getValue()) < 0);
	}

	@Override
	public Value lessEqual(Value arg) {
		return new BooleanValue(value.compareTo((BigDecimal) arg.getValue()) <= 0);
	}

	public Value multiplicationToInteger(IntegerValue v) {
		return new MoneyValue(
				value.multiply(MoneyValue.integerToBigDecimal(v.getValue())).setScale(2, RoundingMode.HALF_UP));
	}

	public static BigDecimal integerToBigDecimal(Integer integer) {
		return MoneyValue.stringToBigDecimal(integer.toString());
	}

	public static BigDecimal stringToBigDecimal(String value) {
		return BigDecimal.valueOf(Double.parseDouble(value)).setScale(2, RoundingMode.HALF_UP);
	}

}
