package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.EvaluatorVisitor;
import nl.nicasso.ql.TypeCheckerVisitor;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.type.BooleanType;
import nl.nicasso.ql.ast.type.Type;

public class BooleanLit extends Literal implements Traversable {

	private final Type type;
	private final Boolean lit;

	public BooleanLit(Boolean lit) {
		this.lit = lit;
		this.type = new BooleanType();
	}

	@Override
	public Boolean getValue() {
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
