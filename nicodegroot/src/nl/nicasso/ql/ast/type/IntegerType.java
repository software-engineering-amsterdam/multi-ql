package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.CodeLocation;

public class IntegerType extends NumericType {

	private final String type;

	public IntegerType() {
		this.type = "Integer";
	}
	
	public IntegerType(CodeLocation location) {
		super(location);
		this.type = "Integer";
	}

	public String getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object ob) {
		IntegerType t2 = (IntegerType) ob;
		return type.equals(t2.getType());
	}
	
	@Override
	public int hashCode(){
	    return type.hashCode();
    }
}
