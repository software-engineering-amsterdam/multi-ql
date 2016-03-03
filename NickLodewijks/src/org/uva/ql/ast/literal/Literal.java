package org.uva.ql.ast.literal;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;

public abstract class Literal<T> extends ASTNode {

	private final T value;

	public Literal(ParserRuleContext context, T value) {
		super(context);
		this.value = value;
	}

	public final T getValue() {
		return value;
	}
}
