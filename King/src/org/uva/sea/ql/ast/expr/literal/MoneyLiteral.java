package org.uva.sea.ql.ast.expr.literal;

import org.joda.money.Money;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.type.MoneyType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class MoneyLiteral extends Expr {

	private final Money value;

	public MoneyLiteral(Money value) {
		this.value = value;
	}

	public Money getValue() {
		return value;
	}

	public MoneyType getType() {
		return new MoneyType();
	}

	@Override
	public <T> T accept(QLNodeVisitor<T> visitor) {
		return visitor.visit(this);

	}

}
