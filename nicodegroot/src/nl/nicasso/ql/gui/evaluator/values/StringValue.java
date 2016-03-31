package nl.nicasso.ql.gui.evaluator.values;

public class StringValue extends Value {

	private final String value;

	public StringValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof StringValue)) {
			return false;
		}
		StringValue value = (StringValue) ob;
		return this.value.equals(value.getValue());
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public Value equal(Value arg) {
		return new BooleanValue(value.equals((String) arg.getValue()));
	}

	@Override
	public Value notEqual(Value arg) {
		return new BooleanValue(!value.equals((String) arg.getValue()));
	}
}
