package nl.nicasso.ql.values;

public class BooleanValue extends Value {

	private final Boolean value;
	
	public BooleanValue(Boolean value) {
		this.value = value;
	}
	
	@Override
	public Boolean getValue() {
		return value;
	}
	
	public Value equal(BooleanValue arg) {
		return new BooleanValue(value == (Boolean) arg.getValue());
	}
	
	public Value notEqual(BooleanValue arg) {
		return new BooleanValue(value != (Boolean) arg.getValue());
	}
	
	public Value and(BooleanValue arg) {
		return new BooleanValue(value && (Boolean) arg.getValue());
	}
	
	public Value or(BooleanValue arg) {
		return new BooleanValue(value || (Boolean) arg.getValue());
	}
	
	public Value not() {
		return new BooleanValue(!value);
	}
	
	
	
}