package uva.TaxForm.Interfaces;

import uva.TaxForm.AST.ASTBlock;
import uva.TaxForm.AST.ASTExpression;
import uva.TaxForm.AST.ASTForm;
import uva.TaxForm.AST.ASTIfStatement;
import uva.TaxForm.AST.ASTNode;
import uva.TaxForm.AST.ASTNumber;
import uva.TaxForm.AST.ASTQuestion;
import uva.TaxForm.AST.ASTVariable;

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
