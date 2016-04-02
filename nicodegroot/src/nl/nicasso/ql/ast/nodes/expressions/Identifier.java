package nl.nicasso.ql.ast.nodes.expressions;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class Identifier extends Expression {

	private final String literal;

	public Identifier(String literal, CodeLocation location) {
		super(location);
		this.literal = literal;
	}

	public String getIdentifier() {
		return literal;
	}

	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public boolean equals(Object ob) {
		Identifier id = (Identifier) ob;
		return literal.equals(id.getIdentifier());
	}

	@Override
	public int hashCode() {
		return literal.hashCode();
	}

	@Override
	public String toString() {
		return literal;
	}

}