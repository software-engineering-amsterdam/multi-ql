package nl.nicasso.ql.ast.type;

public class IntegerType extends Type {

	String value;

	public IntegerType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}