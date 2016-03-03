package org.uva.ql.ast.literal;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;

public abstract class Literal<V> extends ASTNode {

	private final V value;

	public Literal(ParserRuleContext context, V value) {
		super(context);
		this.value = value;
	}

	public final V getValue() {
		return value;
	}

	public abstract <T, U> T accept(LiteralVisitor<T, U> visitor, U context);
}
