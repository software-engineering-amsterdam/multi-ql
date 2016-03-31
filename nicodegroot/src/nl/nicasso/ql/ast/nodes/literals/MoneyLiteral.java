package nl.nicasso.ql.ast.nodes.literals;

import java.math.BigDecimal;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class MoneyLiteral extends Literal {

	private final Type type;
	private final BigDecimal literal;

	public MoneyLiteral(BigDecimal literal) {
		super(null);
		this.type = new MoneyType();
		this.literal = literal;
	}

	public MoneyLiteral(BigDecimal literal, CodeLocation location) {
		super(location);
		this.type = new MoneyType(location);
		this.literal = literal;
	}

	@Override
	public BigDecimal getLiteral() {
		return literal;
	}

	public Type getType() {
		return type;
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof MoneyLiteral)) {
			return false;
		}
		MoneyLiteral lit = (MoneyLiteral) ob;
		return literal.equals(lit.getLiteral());
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