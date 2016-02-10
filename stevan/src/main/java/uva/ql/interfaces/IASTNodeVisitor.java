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
	public void visit(ASTForm form);
	public void visit(ASTNode node);
	public void visit(ASTBlock bblock);
	public void visit(ASTIfStatement ifStatement);
	public void visit(ASTQuestion question);
	public void visit(ASTExpression  expression);
	public void visit(ASTNumber number);
	public void visit(ASTVariable variable);
}
