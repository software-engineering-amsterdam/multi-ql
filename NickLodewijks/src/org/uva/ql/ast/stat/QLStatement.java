package org.uva.ql.ast.stat;

import org.uva.ql.ast.ASTNode;

public abstract class QLStatement extends ASTNode {

	public abstract <T, U> T accept(QLStatementVisitor<T, U> visitor, U context);
}
