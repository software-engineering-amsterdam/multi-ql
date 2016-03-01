package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.CodeLocation;

public class DecimalType extends NumericType {

	private final String type;
	
	public DecimalType() {
		this.type = "Decimal";
	}

	public DecimalType(CodeLocation location) {
		super(location);
		this.type = "Decimal";
	}

	public String getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object ob) {
		DecimalType t2 = (DecimalType) ob;
		return type.equals(t2.getType());
	}
	
	@Override
	public int hashCode(){
	    return type.hashCode();
    }
}
