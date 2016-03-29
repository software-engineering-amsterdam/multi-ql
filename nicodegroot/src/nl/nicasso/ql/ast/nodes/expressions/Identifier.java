package nl.nicasso.ql.ast.nodes.expressions;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class Identifier extends Expression {

	private final String lit;

	public Identifier(String lit, CodeLocation location) {
		super(location);
		this.lit = lit;
	}

	public String getIdentifier() {
		return lit;
	}

	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public boolean equals(Object ob) {
		Identifier id = (Identifier) ob;
		return lit.equals(id.getIdentifier());
	}

	@Override
	public int hashCode() {
		return lit.hashCode();
	}

	@Override
	public String toString() {
		return lit;
	}

}