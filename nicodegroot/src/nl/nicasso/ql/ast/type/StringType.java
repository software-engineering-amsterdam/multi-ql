package nl.nicasso.ql.ast.type;

public class StringType extends Type {

	String value;

	public StringType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}