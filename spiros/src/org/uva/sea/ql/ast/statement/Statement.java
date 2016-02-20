package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public abstract class Statement extends ASTNode {
	
	public Statement(CodeFragment fragment) {
		super(fragment);
	}
	
	public abstract <T> T accept(StatementVisitor<T> visitor);
	

}