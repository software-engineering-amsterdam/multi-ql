package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class Literal<T> extends ASTNode {

	
	private final T value;
	private final ValueType type;

	public Literal(ValueType type, T value) {
		this.value = value;
		this.type = type;
	}

	public T getValue() {
		return value;
	}

	public final ValueType type() {
		return type;
	}

	@Override
	public void accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub

	}
	

}
