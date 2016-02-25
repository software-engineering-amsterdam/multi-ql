package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.Type;

public class IntegerLit extends Literal implements Traversable {

	private final Type type;
	private final Integer lit;

	public IntegerLit(Integer lit) {
		this.lit = lit;
		this.type = new IntegerType();
	}

	@Override
	public Integer getValue() {
		return lit;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}