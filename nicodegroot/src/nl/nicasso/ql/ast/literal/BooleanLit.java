package nl.nicasso.ql.ast.literal;

public class BooleanLit extends Literal {

	boolean lit;

	public BooleanLit(boolean lit) {
		this.lit = lit;
	}

	public boolean getValue() {
		return lit;
	}
	
}
