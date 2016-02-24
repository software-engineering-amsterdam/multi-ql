package uva.ql.interfaces;

import uva.ql.ast.ANode;
import uva.ql.deprecated.ASTBlock;
import uva.ql.deprecated.ASTExpression;
import uva.ql.deprecated.ASTForm;
import uva.ql.deprecated.ASTIfStatement;
import uva.ql.deprecated.ASTNode;
import uva.ql.deprecated.ASTNumber;
import uva.ql.deprecated.ASTQuestion;
import uva.ql.deprecated.ASTVariable;

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
