package nl.nicasso.ql.ast.type;

public class NumericType extends Type {

	private final String type;
	
	public NumericType() {
		super();
		this.type = "Numeric";
	}

	public String getType() {
		return type;
	}
}
