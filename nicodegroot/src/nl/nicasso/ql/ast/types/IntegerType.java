package nl.nicasso.ql.ast.types;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.values.IntegerValue;
import nl.nicasso.ql.visitors.TypeVisitor;

public class IntegerType extends NumericType {

	private final String type;

	public IntegerType() {
		this.type = "Integer";
	}
	
	@Override
	public IntegerValue getDefaultValue() {
		return new IntegerValue(0);
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
		Type t2 = (Type) ob;
		return type.equals(t2.getType());
	}
	
	@Override
	public int hashCode(){
	    return type.hashCode();
    }
	
	@Override
	public <T, U> T accept(TypeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
