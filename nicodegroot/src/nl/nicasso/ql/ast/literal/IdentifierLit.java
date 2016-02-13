package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class IdentifierLit extends Literal implements Traversable {

	String lit;

	public IdentifierLit(String lit) {
		this.lit = lit;
	}

	public String getValue() {
		return lit;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}