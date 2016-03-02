package org.uva.sea.ql.type;

public class Undefined extends Type {

	@Override
	public boolean equals(Object t) {
		return t instanceof Undefined;
	}

	@Override
	public String toString() {
		return "Undefined";
	}
	
}
