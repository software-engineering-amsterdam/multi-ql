package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class Identifier extends Expression implements Traversable {

	private final String lit;

	public Identifier(String lit) {
		this.lit = lit;
	}

	public String getValue() {
		return lit;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
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