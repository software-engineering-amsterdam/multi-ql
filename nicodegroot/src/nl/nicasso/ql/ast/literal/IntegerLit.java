package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.EvaluatorVisitor;
import nl.nicasso.ql.TypeCheckerVisitor;
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
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Type accept(TypeCheckerVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public Literal accept(EvaluatorVisitor visitor) {
		return visitor.visit(this);
	}
	
}