package sc.ql.ast.stat;

import sc.ql.ast.ASTNode;

public abstract class Statement extends ASTNode {

	public abstract <T, U> T accept(StatementVisitor<T, U> visitor, U context);
}
