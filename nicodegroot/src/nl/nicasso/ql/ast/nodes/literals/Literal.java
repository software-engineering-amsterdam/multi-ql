package nl.nicasso.ql.ast.nodes.literals;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.types.Type;

public abstract class Literal extends Expression {

	private Type type;

	public Literal(CodeLocation location) {
		super(location);
	}

	public Type getType() {
		return type;
	}

	public Object getLiteral() {
		return null;
	}

}