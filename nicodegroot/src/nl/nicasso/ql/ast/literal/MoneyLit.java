package nl.nicasso.ql.ast.literal;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.type.MoneyType;
import nl.nicasso.ql.ast.type.Type;

public class MoneyLit extends Literal implements Traversable {

	private final Type type;
	private final Integer number;
	private final Integer cents;

	public MoneyLit(Integer number, Integer cents) {
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
	
	public Type getType() {
		return type;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}