package nl.nicasso.ql.ast.literals;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.types.StringType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class StringLit extends Literal {

	private final Type type;
	private final String lit;

	public StringLit(String lit) {
		super(null);
		this.lit = removeStringQuotes(lit);
		this.type = new StringType();
	}
	
	public StringLit(String lit, CodeLocation location) {
		super(location);
		this.lit = removeStringQuotes(lit);
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
		StringLit lit2 = (StringLit) ob;
		return lit.equals(lit2.getValue());
	}
	
	@Override
	public int hashCode(){
	    return lit.hashCode();
    }
	
	private String removeStringQuotes(String str) {
		str = str.substring(1);
		str = str.substring(0, str.length()-1);
		
		return str;

	}
	
}