package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.CodeLocation;

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
		NumericType t2 = (NumericType) ob;
		return type.equals(t2.getType());
	}
	
	@Override
	public int hashCode(){
	    return type.hashCode();
    }
}
