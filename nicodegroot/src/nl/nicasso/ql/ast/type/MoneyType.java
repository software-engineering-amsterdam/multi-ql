package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.CodeLocation;

public class MoneyType extends NumericType {

	private final String type;

	public MoneyType() {
		this.type = "Money";
	}
	
	public MoneyType(CodeLocation location) {
		super(location);
		this.type = "Money";
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
