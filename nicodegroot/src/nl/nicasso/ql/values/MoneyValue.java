package nl.nicasso.ql.values;

import java.math.BigDecimal;

public class MoneyValue extends Value {
	
	private final BigDecimal value;
	
	public MoneyValue(BigDecimal value) {
		this.value = value;
	}
	
	@Override
	public BigDecimal getValue() {
		return value;
	}
	
	public Value addition(MoneyValue arg) {
		return new MoneyValue(value.add((BigDecimal) arg.getValue()));
	}
	
	public Value subtraction(MoneyValue arg) {
		return new MoneyValue(value.subtract((BigDecimal) arg.getValue()));
	}
	
	public Value equal(MoneyValue arg) {
		return new BooleanValue(value.equals((BigDecimal) arg.getValue()));
	}
	
	public Value notEqual(MoneyValue arg) {
		return new BooleanValue(!value.equals((BigDecimal) arg.getValue()));
	}
	
	public Value division(MoneyValue arg) {
		return new MoneyValue(value.divide((BigDecimal) arg.getValue()));
	}
	
	public Value multiplication(MoneyValue arg) {
		return new MoneyValue(value.multiply((BigDecimal) arg.getValue()));
	}
	
	public Value greater(MoneyValue arg) {
		return new BooleanValue(value.compareTo((BigDecimal) arg.getValue()) > 0);
	}
	
	public Value greaterEqual(MoneyValue arg) {
		return new BooleanValue(value.compareTo((BigDecimal) arg.getValue()) >= 0);
	}
	
	public Value less(MoneyValue arg) {
		return new BooleanValue(value.compareTo((BigDecimal) arg.getValue()) < 0);
	}
	
	public Value lessEqual(MoneyValue arg) {
		return new BooleanValue(value.compareTo((BigDecimal) arg.getValue()) <= 0);
	}
}
