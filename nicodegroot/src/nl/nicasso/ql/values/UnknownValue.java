package nl.nicasso.ql.values;

public class UnknownValue extends Value {

	private final String value;
	
	public UnknownValue() {
		this.value = "";
	}
	
	@Override
	public boolean equals(Object ob) {
		StringValue value = (StringValue) ob;
		return value.equals(value.getValue());
	}
	
	@Override
	public int hashCode(){
	    return value.hashCode();
    }
	
	@Override
	public String getValue() {
		return value;
	}
}