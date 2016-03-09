package org.uva.sea.ql.type;

public class StrType extends Type {
	
	@Override
	public boolean equals(Object t) {
		return t instanceof StrType;
	}

	@Override
	public String toString() {
		return "String";
	}

	@Override
	public Object parse(String message) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
