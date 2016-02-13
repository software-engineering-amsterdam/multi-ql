package nl.nicasso.ql.ast.literal;

public class StringLit extends Literal {

	String lit;

	public StringLit(String lit) {
		this.lit = lit;
	}

	public String getValue() {
		return lit;
	}
	
}