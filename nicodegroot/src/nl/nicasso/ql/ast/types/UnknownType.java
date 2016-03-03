package nl.nicasso.ql.ast.types;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.values.UnknownValue;

public class UnknownType extends Type {

	private final String type;

	public UnknownType() {
		this.type = "Unknown";
	}
	
	@Override
	public UnknownValue getDefaultValue() {
		return new UnknownValue();
	}
	
	public UnknownType(CodeLocation location) {
		super(location);
		this.type = "Unknown";
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