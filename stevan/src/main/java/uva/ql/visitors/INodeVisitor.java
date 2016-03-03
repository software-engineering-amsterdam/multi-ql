package uva.ql.visitors;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.Question;
import uva.ql.ast.conditionals.abstracts.Condition;
import uva.ql.ast.numbers.abstracts.Number;
import uva.ql.ast.variables.abstracts.Variable;

public interface INodeVisitor {

	public void visitForm(Form form);
	public void visitBlock(Block block);
	public void visitQuestion(Question question);
	public void visitVar(Variable variable);
	public void visitNum(Number number);
	
	public <T> void visitExp(T expression);
	public void visitCondition(Condition condition);
}
