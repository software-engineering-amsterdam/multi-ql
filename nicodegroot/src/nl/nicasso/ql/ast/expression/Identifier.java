package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.visitor.ExpressionVisitor;

public class Identifier extends Expression {

	private final String lit;

	public Identifier(String lit, CodeLocation location) {
		super(location);
		this.lit = lit;
	}

	public String getValue() {
		return lit;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object ob) {
		Identifier id = (Identifier) ob;
		return lit.equals(id.getValue());
	}
	
	@Override
	public int hashCode(){
	    return lit.hashCode();
    }
	
}