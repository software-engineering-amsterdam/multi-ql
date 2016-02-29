package nl.nicasso.ql.ast.type;

public class BooleanType extends Type {

	private final String type;

	public BooleanType() {
		super();
		this.type = "Boolean";
	}

	public String getType() {
		return type;
	}
	
	public boolean compatibleWith(Type type) {
		switch(type.getType()) {
			case "Boolean":
				return true;
			case "Integer":
				return false;
			case "Money":
				return false;
			case "String":
				return false;
			default:
				return false;
		}
	}
	
}
