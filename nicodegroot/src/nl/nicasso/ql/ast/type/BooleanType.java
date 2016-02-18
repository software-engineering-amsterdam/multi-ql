package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.TypeChecker;

public class BooleanType extends Type {

	String type;

	public BooleanType() {
		super();
		this.type = "Boolean";
	}

	public String getType() {
		return type;
	}
	
	@Override
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}
	
}
