package nl.nicasso.ql.ast.nodes.literals;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.StringType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class StringLiteral extends Literal {

	private final Type type;
	private final String literal;

	public StringLiteral(String literal) {
		super(null);
		this.literal = literal;
		this.type = new StringType();
	}

	public StringLiteral(String literal, CodeLocation location) {
		super(location);
		this.literal = literal;
		this.type = new StringType(location);
	}

	@Override
	public String getLiteral() {
		return literal;
	}

	public Type getType() {
		return type;
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof StringLiteral)) {
			return false;
		}
		StringLiteral lit2 = (StringLiteral) ob;
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
		return literal;
	}

}