package nl.nicasso.ql.ast.types;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.values.StringValue;
import nl.nicasso.ql.visitors.TypeVisitor;

public class StringType extends Type {

	private final String type;

	public StringType() {
		this.type = "String";
	}
	
	@Override
	public StringValue getDefaultValue() {
		return new StringValue("");
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
		if (!(ob instanceof Type)) {
			return false;
		}
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
