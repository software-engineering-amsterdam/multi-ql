package nl.nicasso.ql.ast.type;

public class IntegerType extends NumericType {

	private final String type;

	public IntegerType() {
		super();
		this.type = "Integer";
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
