package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTSourceInfo;

public abstract class Expr extends ASTNode {

	public Expr(ASTSourceInfo sourceInfo) {
		super(sourceInfo);
	}

	public abstract <T, U> T accept(ExprVisitor<T, U> visitor, U context);
}
