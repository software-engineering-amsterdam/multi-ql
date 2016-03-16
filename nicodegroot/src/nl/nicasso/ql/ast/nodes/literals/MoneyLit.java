package nl.nicasso.ql.ast.nodes.literals;

import java.math.BigDecimal;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class MoneyLit extends Literal {

	private final Type type;
	private final BigDecimal value;
	
	public MoneyLit(BigDecimal value) {
		super(null);
		this.type = new MoneyType();
		this.value = value;
	}
	
	public MoneyLit(BigDecimal value, CodeLocation location) {
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
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof MoneyLit)) {
			return false;
		}
		MoneyLit lit = (MoneyLit) ob;
		return value.equals(lit.getValue());
	}
	
	@Override
	public int hashCode(){
	    return value.hashCode();
    }

}