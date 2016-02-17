package org.uva.sea.ql.ast.expr.literal;

import org.joda.money.Money;
import org.uva.sea.ql.ast.ValueType;

public class MoneyLiteral extends Literal<Money> {

	public MoneyLiteral(Money value) {
		super(ValueType.MONEY, value);
		// TODO Auto-generated constructor stub
	}

}
