package org.uva.sea.ql.ast.expr.literal;

import org.joda.money.Money;
import org.uva.sea.ql.ast.expr.type.MoneyType;

public class MoneyLiteral extends Literal<Money> {

	public MoneyLiteral(Money value) {
		super(new MoneyType(), value);
		// TODO Auto-generated constructor stub
	}

}
