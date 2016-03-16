package nl.nicasso.ql.ast.literals;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class BooleanLit extends Literal {

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
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof BooleanLit)) {
			return false;
		}
		BooleanLit lit2 = (BooleanLit) ob;
		return lit.equals(lit2.getValue());
	}
	
	@Override
	public int hashCode(){
	    return lit.hashCode();
    }
}
