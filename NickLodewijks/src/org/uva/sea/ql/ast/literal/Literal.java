package org.uva.sea.ql.ast.literal;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.ValueType;

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

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Result validate() {
		return Result.TRUE();
	}
}
