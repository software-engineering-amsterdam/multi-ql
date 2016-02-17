package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNode;

public abstract class Expr extends ASTNode {

	public abstract Object interpret(Context context);
}
