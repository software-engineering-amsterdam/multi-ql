package ql2.ast.literal;

import ql2.BaseVisitor;
import ql2.ast.ASTNode;

abstract public class Literal<T> extends ASTNode {

	protected T value;
	
	public Literal(T lit) {
		this.value = lit;
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(T val) {
		value = val;
	}

}
