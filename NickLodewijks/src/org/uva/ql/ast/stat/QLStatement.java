package org.uva.ql.ast.stat;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;

public abstract class QLStatement extends ASTNode {

	public QLStatement(ParserRuleContext context) {
		super(context);
	}

	public abstract <T, U> T accept(QLStatementVisitor<T, U> visitor, U context);
}
