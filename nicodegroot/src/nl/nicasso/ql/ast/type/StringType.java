package nl.nicasso.ql.ast.type;

public class StringType extends Type {

	private final String type;

	public StringType() {
		super();
		this.type = "String";
	}

	public String getType() {
		return type;
	}
	
	public boolean compatibleWith(Type type) {
		switch(type.getType()) {
			case "Boolean":
				return false;
			case "Integer":
				return false;
			case "Money":
				return false;
			case "String":
				return true;
			default:
				return false;
		}
	}
	
}
