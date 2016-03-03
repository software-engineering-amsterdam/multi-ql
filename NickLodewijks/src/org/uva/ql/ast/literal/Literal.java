package org.uva.ql.ast.literal;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;

public abstract class Literal extends ASTNode {

	public Literal(ParserRuleContext context) {
		super(context);
	}

	public abstract Object getValue();

	public abstract <T, U> T accept(LiteralVisitor<T, U> visitor, U context);
}
