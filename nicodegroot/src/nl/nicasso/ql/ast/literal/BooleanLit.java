package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class BooleanLit extends Literal implements Traversable {

	boolean lit;

	public BooleanLit(boolean lit) {
		this.lit = lit;
	}

	public boolean getValue() {
		return lit;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
