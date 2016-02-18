package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.TypeChecker;

public class StringType extends Type {

	String type;

	public StringType() {
		super();
		this.type = "String";
	}

	public String getType() {
		return type;
	}
	
	@Override
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}
	
}
