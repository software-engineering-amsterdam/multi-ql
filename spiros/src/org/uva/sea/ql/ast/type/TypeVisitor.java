package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;

public interface TypeVisitor<ASTNode> {

	public ASTNode visit(BoolType node);
	public ASTNode visit(DoubleType node);
	public ASTNode visit(IntType node);
	public ASTNode visit(StrType node);
	public ASTNode visit(UndefinedType node);
}
