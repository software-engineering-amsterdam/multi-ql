package nl.nicasso.ql.ast.literal;

public class IntegerLit extends Literal {

	int lit;

	public IntegerLit(int lit) {
		this.lit = lit;
	}

	public int getValue() {
		return lit;
	}
	
}