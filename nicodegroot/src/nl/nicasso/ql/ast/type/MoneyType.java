package nl.nicasso.ql.ast.type;

public class MoneyType extends Type {

	String value;

	public MoneyType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
