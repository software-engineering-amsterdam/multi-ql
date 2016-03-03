package org.uva.sea.ql.type;

public class MoneyType extends Type {
	
	@Override
	public boolean equals(Object t) {
		return t instanceof MoneyType;
	}

	@Override
	public String toString() {
		return "Money";
	}
	
}
