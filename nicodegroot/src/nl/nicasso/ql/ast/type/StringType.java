package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.CodeLocation;

public class StringType extends Type {

	private final String type;

	public StringType() {
		this.type = "String";
	}
	
	public StringType(CodeLocation location) {
		super(location);
		this.type = "String";
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
}
