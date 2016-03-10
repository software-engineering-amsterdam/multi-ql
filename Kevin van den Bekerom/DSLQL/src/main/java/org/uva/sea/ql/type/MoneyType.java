package org.uva.sea.ql.type;

import org.uva.sea.ql.value.MoneyValue;
import org.uva.sea.ql.value.Value;

public class MoneyType extends Type {
	
	@Override
	public boolean equals(Object t) {
		return t instanceof MoneyType;
	}

	@Override
	public String toString() {
		return "Money";
	}

	@Override
	public Value parse(String message) {
		return new MoneyValue(Double.parseDouble(message));
	}
	
}
