package ql2.ast.literal;

import ql2.BaseVisitor;
import ql2.ast.ASTNode;

abstract public class Literal<T> extends ASTNode {

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
