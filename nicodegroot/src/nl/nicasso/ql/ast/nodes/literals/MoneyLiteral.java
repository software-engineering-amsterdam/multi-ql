package nl.nicasso.ql.ast.nodes.literals;

import java.math.BigDecimal;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class MoneyLiteral extends Literal {

	private final Type type;
	private final BigDecimal value;
	
	public MoneyLiteral(BigDecimal value) {
		super(null);
		this.type = new MoneyType();
		this.value = value;
	}
	
	public MoneyLiteral(BigDecimal value, CodeLocation location) {
		super(location);
		this.type = new MoneyType(location);
		this.value = value;
	}
	
	@Override
	public BigDecimal getValue() {
		return value;
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
		return value.equals(lit.getValue());
	}
	
	@Override
	public int hashCode(){
	    return value.hashCode();
    }
	
	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

}