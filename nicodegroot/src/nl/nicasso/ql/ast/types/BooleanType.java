package nl.nicasso.ql.ast.types;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.values.BooleanValue;
import nl.nicasso.ql.visitors.TypeVisitor;

public class BooleanType extends Type {

	private final String type;
	
	public BooleanType() {
		this.type = "Boolean";
	}

	public BooleanType(CodeLocation location) {
		super(location);
		this.type = "Boolean";
	}
	
	@Override
	public BooleanValue getDefaultValue() {
		return new BooleanValue(false);
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
