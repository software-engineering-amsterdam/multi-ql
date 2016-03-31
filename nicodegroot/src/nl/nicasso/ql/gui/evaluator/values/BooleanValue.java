package nl.nicasso.ql.gui.evaluator.values;

public class BooleanValue extends Value {

	private final Boolean value;

	public BooleanValue(Boolean value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof BooleanValue)) {
			return false;
		}
		BooleanValue value = (BooleanValue) ob;
		return this.value.equals(value.getValue());
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public Value equal(Value arg) {
		return new BooleanValue(value == (Boolean) arg.getValue());
	}

	@Override
	public Value notEqual(Value arg) {
		return new BooleanValue(value != (Boolean) arg.getValue());
	}

	@Override
	public Value and(Value arg) {
		return new BooleanValue(value && (Boolean) arg.getValue());
	}

	@Override
	public Value or(Value arg) {
		return new BooleanValue(value || (Boolean) arg.getValue());
	}

	@Override
	public Value not() {
		return new BooleanValue(!value);
	}

}