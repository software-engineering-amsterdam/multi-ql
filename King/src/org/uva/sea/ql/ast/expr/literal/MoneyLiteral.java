package org.uva.sea.ql.ast.expr.literal;

import org.joda.money.Money;
import org.uva.sea.ql.ast.expr.type.MoneyType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class MoneyLiteral extends Literal<Money> {

	public MoneyLiteral(Money value) {
		super(new MoneyType(), value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Type accept(QLNodeVisitor visitor) {
		return visitor.visit(this);

	}

}
