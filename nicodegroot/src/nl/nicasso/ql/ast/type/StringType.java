package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.EvaluatorVisitor;
import nl.nicasso.ql.TypeCheckerVisitor;
import nl.nicasso.ql.ast.literal.Literal;

public class StringType extends Type {

	private final String type;

	public StringType() {
		super();
		this.type = "String";
	}

	public String getType() {
		return type;
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
