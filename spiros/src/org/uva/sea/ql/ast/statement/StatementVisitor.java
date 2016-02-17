package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.block.Block;

public interface StatementVisitor<ASTNode> {

	public ASTNode visit(Block block);
	public ASTNode visit(ComputedQuestion computedQuestion);
	public ASTNode visit(Question question);
	public ASTNode visit(IfStatement ifStatement);
	public ASTNode visit(IfElseStatement ifElseStatement);
	
}


