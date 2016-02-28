package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class Literal<T> extends ASTNode {

	
	private final T value;
	private final Type type;

	public Literal(Type type, T value) {
		this.value = value;
		this.type = type;
	}

	public T getValue() {
		return value;
	}

	public final Type getType() {
		return type;
	}

	@Override
	public Type accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	
	

}
