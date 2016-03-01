package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.type.Type;

public abstract class Literal extends Expression {

	Type type;
	
	public Literal(CodeLocation location) {
		super(location);
	}
	
	public Type getType() {
		return type;
	}
	
	public Object getValue() {
		return null;
	}
	
}