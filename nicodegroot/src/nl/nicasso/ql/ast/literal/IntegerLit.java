package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.visitor.Visitor;

public class IntegerLit extends Literal implements Traversable {

	private final Type type;
	private final Integer lit;

	public IntegerLit(Integer lit) {
		super(null);
		this.lit = lit;
		this.type = new IntegerType();
	}
	
	public IntegerLit(Integer lit, CodeLocation location) {
		super(location);
		this.lit = lit;
		this.type = new IntegerType(location);
	}

	@Override
	public Integer getValue() {
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
		IntegerLit lit2 = (IntegerLit) ob;
		return lit.equals(lit2.getValue());
	}
	
	@Override
	public int hashCode(){
	    return lit.hashCode();
    }

}