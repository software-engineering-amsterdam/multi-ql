package ql2.ast.literal;

import ql2.BaseVisitor;
import ql2.Currency;

public class CurrencyLiteral extends Literal<Currency> {
	
	public CurrencyLiteral(Currency value) {
		super(value);
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
