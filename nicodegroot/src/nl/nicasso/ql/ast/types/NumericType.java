package nl.nicasso.ql.ast.types;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.visitors.TypeVisitor;

public class NumericType extends Type {

	private final String type;
	
	public NumericType() {
		this.type = "Numeric";
	}
	
	public NumericType(CodeLocation location) {
		super(location);
		this.type = "Numeric";
	}

	public String getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object ob) {
		Type t2 = (Type) ob;
		return type.equals(t2.getType());
	}
	
	@Override
	public int hashCode(){
	    return type.hashCode();
    }

	@Override
	public <T, U> T accept(TypeVisitor<T, U> visitor, U context) {
		// Make better
		assert false;
		return null;
	}
}