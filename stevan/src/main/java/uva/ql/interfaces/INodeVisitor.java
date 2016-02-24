package uva.ql.interfaces;

import uva.ql.ast.ANumber;
import uva.ql.ast.AVariable;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.IfStatement;
import uva.ql.ast.Question;

public interface INodeVisitor {

	public void visitForm(Form form);
	public void visitBlock(Block block);
	public void visitIfStmnt(IfStatement ifStatement);
	public void visitQuestion(Question question);
	public void visitVar(AVariable variable);
	public void visitNum(ANumber number);
	
	public <T> void visitExp(T expression);
}
