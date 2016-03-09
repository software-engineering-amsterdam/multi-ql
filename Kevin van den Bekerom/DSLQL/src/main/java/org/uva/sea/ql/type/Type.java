package org.uva.sea.ql.type;

public abstract class Type {
	
	@Override
	public abstract boolean equals(Object t);
	
	@Override 
	public abstract String toString();
	
	public abstract Object parse(String message);
}
