package nl.nicasso.ql.ast.nodes.literals;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class IntegerLiteral extends Literal {

	private final Type type;
	private final Integer literal;

	public IntegerLiteral(Integer literal) {
		super(null);
		this.literal = literal;
		this.type = new IntegerType();
	}

	public IntegerLiteral(Integer literal, CodeLocation location) {
		super(location);
		this.literal = literal;
		this.type = new IntegerType(location);
	}

	@Override
	public Integer getLiteral() {
		return literal;
	}

	public Type getType() {
		return type;
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof IntegerLiteral)) {
			return false;
		}
		IntegerLiteral lit2 = (IntegerLiteral) ob;
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