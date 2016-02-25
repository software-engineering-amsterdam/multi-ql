package nl.nicasso.ql.ast.type;

public class MoneyType extends NumericType {

	private final String type;

	public MoneyType() {
		super();
		this.type = "Money";
	}

	public String getType() {
		return type;
	}
	
	public boolean CompatibleWith(Type type) {
		switch(type.getType()) {
			case "Boolean":
				return false;
			case "Integer":
				return true;
			case "Money":
				return true;
			case "String":
				return false;
			default:
				return false;
		}
	}
}
