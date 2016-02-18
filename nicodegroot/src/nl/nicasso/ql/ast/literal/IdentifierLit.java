package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.TypeChecker;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.type.Type;

public class IdentifierLit extends Literal implements Traversable {

	Type type;
	String lit;

	public IdentifierLit(String lit, Type type) {
		this.lit = lit;
		this.type = type;
	}

	public String getValue() {
		return lit;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}
	
}