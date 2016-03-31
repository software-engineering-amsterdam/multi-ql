package nl.nicasso.ql.gui.evaluator.values;

public class UnknownValue extends Value {

	private final String value;

	public UnknownValue() {
		this.value = "Unknown";
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof UnknownValue)) {
			return false;
		}
		UnknownValue value = (UnknownValue) ob;
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
}