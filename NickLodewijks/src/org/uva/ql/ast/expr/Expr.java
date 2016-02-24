package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;

public abstract class Expr extends ASTNode {

	public Expr(ParserRuleContext context) {
		super(context);
	}

	public abstract Object interpret(Context context);
}
