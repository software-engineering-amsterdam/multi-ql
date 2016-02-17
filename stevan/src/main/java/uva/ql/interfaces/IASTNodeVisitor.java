package uva.ql.interfaces;

import uva.ql.ast.ASTBlock;
import uva.ql.ast.ASTExpression;
import uva.ql.ast.ASTForm;
import uva.ql.ast.ASTIfStatement;
import uva.ql.ast.ASTNode;
import uva.ql.ast.ASTNumber;
import uva.ql.ast.ASTQuestion;
import uva.ql.ast.ASTVariable;

public interface IASTNodeVisitor {
	public void visitForm(ASTForm form);
	public void visitNode(ASTNode node);
	public void visitBlock(ASTBlock block);
	public void visitIfStmnt(ASTIfStatement ifStatement);
	public void visitQuestion(ASTQuestion question);
	public void visitExp(ASTExpression  expression);
	public void visitNum(ASTNumber number);
	public void visitVar(ASTVariable variable);
}
