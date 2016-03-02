package nl.nicasso.ql.ast.literal;

import java.math.BigDecimal;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.type.MoneyType;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.visitor.ExpressionVisitor;

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

	/*
	private final Integer number;
	private final Integer cents;

	public DecimalLit(Integer number, Integer cents) {
		this.number = number;
		this.cents = cents;
		this.type = new MoneyType();
	}

	@Override
	public Integer getValue() {
		if (cents.equals(00)) {
			return number * 100;
		} else {
			return Integer.parseInt(Integer.toString(number) + Integer.toString(cents));
		}
	}
	*/
	public Type getType() {
		return type;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object ob) {
		MoneyLit lit = (MoneyLit) ob;
		return value.equals(lit.getValue());
	}
	
	@Override
	public int hashCode(){
	    return value.hashCode();
    }

}