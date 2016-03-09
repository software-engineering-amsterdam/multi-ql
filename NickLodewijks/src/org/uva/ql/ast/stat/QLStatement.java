package org.uva.ql.ast.stat;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTSourceInfo;

public abstract class QLStatement extends ASTNode {

	public QLStatement(ASTSourceInfo context) {
		super(context);
	}

	public abstract <T, U> T accept(QLStatementVisitor<T, U> visitor, U context);
}
