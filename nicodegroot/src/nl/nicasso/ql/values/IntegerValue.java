package nl.nicasso.ql.values;

import java.math.BigDecimal;

public class IntegerValue extends Value {
	
	private final Integer value;
	
	public IntegerValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object ob) {
		IntegerValue value = (IntegerValue) ob;
		return value.equals(value.getValue());
	}
	
	@Override
	public int hashCode(){
	    return value.hashCode();
    }
	
	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Value addition(Value arg) {
		return arg.addToInt(this);
	}

	@Override
	public Value addToInt(IntegerValue v) {
		return new IntegerValue(value + v.value);
	}
	
	@Override
	public Value subtraction(Value arg) {
		return new IntegerValue(value - (Integer) arg.getValue());
	}
	
	@Override
	public Value equal(Value arg) {
		return new BooleanValue(value.equals((Integer) arg.getValue()));
	}
	
	@Override
	public Value notEqual(Value arg) {
		return new BooleanValue(value.equals((Integer) arg.getValue()));
	}
	
	@Override
	public Value division(Value arg) {
		return new IntegerValue(value / (Integer) arg.getValue());
	}
	
	@Override
	public Value multiplication(MoneyValue arg) {
		System.out.println("MULTI 2");
		return new MoneyValue(BigDecimal.valueOf(value).multiply(arg.getValue()));
	}
	
	@Override
	public Value multiplication(Value arg) {
		System.out.println("MULTI 1");
		return new IntegerValue(value * (Integer) arg.getValue());
	}
	
	@Override
	public Value greater(Value arg) {
		return new BooleanValue(value > (Integer) arg.getValue());
	}
	
	@Override
	public Value greaterEqual(Value arg) {
		return new BooleanValue(value >= (Integer) arg.getValue());
	}
	
	@Override
	public Value less(Value arg) {
		return new BooleanValue(value < (Integer) arg.getValue());
	}
	
	@Override
	public Value lessEqual(Value arg) {
		return new BooleanValue(value <= (Integer) arg.getValue());
	}
	
}
