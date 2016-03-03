package org.uva.sea.ql.type;

public class BoolType extends Type {

	@Override
	public boolean equals(Object t) {
		return t instanceof BoolType;
	}

	@Override
	public String toString() {
		return "Boolean";
	}
}
