package uva.ql.deprecated;

import uva.ql.ast.ANode;

public interface IASTNodeVisitor {
	
	public void visitForm(ASTForm form);
	public void visitNode(ANode node);
	public void visitNode(ASTNode node);
	public void visitBlock(ASTBlock block);
	public void visitIfStmnt(ASTIfStatement ifStatement);
	public void visitQuestion(ASTQuestion question);
	public void visitExp(ASTExpression  expression);
	public void visitNum(ASTNumber number);
	public void visitVar(ASTVariable variable);
}
