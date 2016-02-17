package org.uva.ql.ast.literal;

import org.uva.ql.ast.ASTNode;

public abstract class Literal<T> extends ASTNode {

	private final T value;

	public Literal(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
