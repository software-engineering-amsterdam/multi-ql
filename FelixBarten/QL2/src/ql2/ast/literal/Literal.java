package ql2.ast.literal;

import ql2.ASTNode;
import ql2.BaseVisitor;

abstract public class Literal<T> {

	protected T value;
	
	
	public Literal(T lit) {
		setValue(lit);
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(T val) {
		value = val;
	}

}
