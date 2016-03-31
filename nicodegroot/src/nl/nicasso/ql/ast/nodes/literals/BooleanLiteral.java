package nl.nicasso.ql.ast.nodes.literals;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class BooleanLiteral extends Literal {

	private final Type type;
	private final Boolean literal;

	public BooleanLiteral(Boolean literal) {
		super(null);
		this.literal = literal;
		this.type = new BooleanType();
	}

	public BooleanLiteral(Boolean literal, CodeLocation location) {
		super(location);
		this.literal = literal;
		this.type = new BooleanType(location);
	}

	@Override
	public Boolean getLiteral() {
		return literal;
	}

	public Type getType() {
		return type;
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof BooleanLiteral)) {
			return false;
		}
		BooleanLiteral lit2 = (BooleanLiteral) ob;
		return literal.equals(lit2.getLiteral());
	}

	@Override
	public int hashCode() {
		return literal.hashCode();
	}

	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return literal.toString();
	}
}
