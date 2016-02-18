package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.TypeChecker;

public class IntegerType extends NumericType {

	String type;

	public IntegerType() {
		super();
		this.type = "Integer";
	}

	public String getType() {
		return type;
	}
	
	@Override
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}
}
