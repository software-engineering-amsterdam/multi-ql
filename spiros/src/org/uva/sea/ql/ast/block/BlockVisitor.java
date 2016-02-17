package org.uva.sea.ql.ast.block;


public interface BlockVisitor<ASTNode> {

	public ASTNode visit(Block block);
}
