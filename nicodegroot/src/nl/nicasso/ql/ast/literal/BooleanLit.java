package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.type.BooleanType;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.visitor.Visitor;

public class BooleanLit extends Literal implements Traversable {

	private final Type type;
	private final Boolean lit;
	
	public BooleanLit(Boolean lit) {
		super(null);
		this.lit = lit;
		this.type = new BooleanType();
	}

	public BooleanLit(Boolean lit, CodeLocation location) {
		super(location);
		this.lit = lit;
		this.type = new BooleanType(location);
	}

	@Override
	public Boolean getValue() {
		return lit;
	}
	
	public Type getType() {
		return type;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object ob) {
		BooleanLit lit2 = (BooleanLit) ob;
		return lit.equals(lit2.getValue());
	}
	
	@Override
	public int hashCode(){
	    return lit.hashCode();
    }
}
