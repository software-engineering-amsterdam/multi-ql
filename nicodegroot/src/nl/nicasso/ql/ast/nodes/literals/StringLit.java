package nl.nicasso.ql.ast.nodes.literals;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.StringType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class StringLit extends Literal {

	private final Type type;
	private final String lit;

	public StringLit(String lit) {
		super(null);
		this.lit = lit;
		this.type = new StringType();
	}
	
	public StringLit(String lit, CodeLocation location) {
		super(location);
		this.lit = lit;
		this.type = new StringType(location);
	}

	@Override
	public String getValue() {
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
		if (!(ob instanceof StringLit)) {
			return false;
		}
		StringLit lit2 = (StringLit) ob;
		return lit.equals(lit2.getValue());
	}
	
	@Override
	public int hashCode(){
	    return lit.hashCode();
    }
	
}