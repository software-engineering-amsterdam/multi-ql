package org.uva.sea.ql.type;

public class WildType extends Type {

	@Override
	public boolean equals(Object t) {
		return t instanceof WildType;
	}

	@Override
	public String toString() {
		return "Wildcard";
	}

}
