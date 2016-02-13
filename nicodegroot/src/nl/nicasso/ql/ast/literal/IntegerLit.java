package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class IntegerLit extends Literal implements Traversable {

	int lit;

	public IntegerLit(int lit) {
		this.lit = lit;
	}

	public int getValue() {
		return lit;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}