package org.uva.sea.ql.type;

public class NumericalType extends Type {

	@Override
	public boolean equals(Object t) {
		return (t instanceof MoneyType || t instanceof IntType || t instanceof NumericalType);
	}

	@Override
	public String toString() {
		return "Numerical";
	}

	@Override
	public Object parse(String message) {
		// TODO Auto-generated method stub
		return null;
	}

}
