package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.TypeChecker;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.Type;

public class IntegerLit extends Literal implements Traversable {

	Type type;
	int lit;

	public IntegerLit(int lit) {
		this.lit = lit;
		this.type = new IntegerType();
	}

	public int getValue() {
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