package nl.nicasso.ql.values;

public class IntegerValue extends Value {
	
	private final Integer value;
	
	public IntegerValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public Integer getValue() {
		return value;
	}

	public Value addition(IntegerValue arg) {
		return new IntegerValue(value + (Integer) arg.getValue());
	}
	
	public Value subtraction(IntegerValue arg) {
		return new IntegerValue(value - (Integer) arg.getValue());
	}
	
	public Value equal(IntegerValue arg) {
		return new BooleanValue(value == (Integer) arg.getValue());
	}
	
	public Value notEqual(IntegerValue arg) {
		return new BooleanValue(value != (Integer) arg.getValue());
	}
	
	public Value division(IntegerValue arg) {
		return new IntegerValue(value / (Integer) arg.getValue());
	}
	
	public Value multiplication(IntegerValue arg) {
		return new IntegerValue(value * (Integer) arg.getValue());
	}
	
	public Value greater(IntegerValue arg) {
		return new BooleanValue(value > (Integer) arg.getValue());
	}
	
	public Value greaterEqual(IntegerValue arg) {
		return new BooleanValue(value >= (Integer) arg.getValue());
	}
	
	public Value less(IntegerValue arg) {
		return new BooleanValue(value < (Integer) arg.getValue());
	}
	
	public Value lessEqual(IntegerValue arg) {
		return new BooleanValue(value <= (Integer) arg.getValue());
	}
	
}
