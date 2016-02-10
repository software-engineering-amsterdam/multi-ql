package org.uva.ql.ast.literal;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ValueType;

public abstract class Literal<T> extends ASTNode {

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
}
