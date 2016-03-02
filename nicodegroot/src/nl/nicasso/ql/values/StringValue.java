package nl.nicasso.ql.values;

public class StringValue extends Value {

	private final String value;
	
	public StringValue(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}
