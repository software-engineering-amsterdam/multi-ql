package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.TypeChecker;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.type.BooleanType;
import nl.nicasso.ql.ast.type.Type;

public class BooleanLit extends Literal implements Traversable {

	Type type;
	boolean lit;

	public BooleanLit(boolean lit) {
		this.lit = lit;
		this.type = new BooleanType();
	}

	public boolean getValue() {
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
