package sc.ql.ast.stat;

import sc.ql.ast.ASTNode;

public abstract class QLStatement extends ASTNode {

	public abstract <T, U> T accept(QLStatementVisitor<T, U> visitor, U context);
}
