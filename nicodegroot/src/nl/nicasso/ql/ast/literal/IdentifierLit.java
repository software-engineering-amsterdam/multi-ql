package nl.nicasso.ql.ast.literal;

public class IdentifierLit extends Literal {

	String lit;

	public IdentifierLit(String lit) {
		this.lit = lit;
	}

	public String getValue() {
		return lit;
	}
	
}