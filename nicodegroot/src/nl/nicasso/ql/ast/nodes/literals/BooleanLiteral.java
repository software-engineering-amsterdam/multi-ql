package nl.nicasso.ql.ast.nodes.literals;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class BooleanLiteral extends Literal {

	private final Type type;
	private final Boolean lit;
	
	public BooleanLiteral(Boolean lit) {
		super(null);
		this.lit = lit;
		this.type = new BooleanType();
	}

	public BooleanLiteral(Boolean lit, CodeLocation location) {
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
		if (!(ob instanceof BooleanLiteral)) {
			return false;
		}
		BooleanLiteral lit2 = (BooleanLiteral) ob;
		return lit.equals(lit2.getValue());
	}
	
	@Override
	public int hashCode(){
	    return lit.hashCode();
    }
}
