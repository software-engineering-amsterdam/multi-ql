package nl.nicasso.ql.ast.type;

public class BooleanType extends Type {

	String value;

	public BooleanType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}