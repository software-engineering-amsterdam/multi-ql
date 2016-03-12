package nl.nicasso.ql.values;

import java.math.BigDecimal;

public class IntegerValue extends Value {
	
	private final Integer value;
	
	public IntegerValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object ob) {
		System.out.println(ob.getClass());
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
		return new IntegerValue(value + (Integer) arg.getValue());
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
		return arg.divisionToInteger(this);
	}
	
	@Override
	public Value multiplication(Value arg) {
		return arg.multiplicationToInteger(this);
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
	
	// DOUBLE DISPATCHING
	
	@Override
	public Value divisionToMoney(MoneyValue v) {
		return new MoneyValue(v.getValue().divide(BigDecimal.valueOf(Double.parseDouble(Integer.toString(value)))));
	}
	
	@Override
	public Value multiplicationToMoney(MoneyValue v) {
		return new MoneyValue(v.getValue().multiply(BigDecimal.valueOf(Double.parseDouble(Integer.toString(value)))));
	}
}
