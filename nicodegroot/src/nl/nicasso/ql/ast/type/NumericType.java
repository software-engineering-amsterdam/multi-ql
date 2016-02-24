package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.TypeChecker;

public class NumericType extends Type {

	String type;
	
	public NumericType() {
		super();
		this.type = "Numeric";
	}

	public String getType() {
		return type;
	}
	
	@Override
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}
	
}
