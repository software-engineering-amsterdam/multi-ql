package nl.nicasso.ql.ast.literals;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.types.Type;

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