package uva.ql.visitors;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.condition.CondIfElseStatement;
import uva.ql.ast.condition.CondIfStatement;
import uva.ql.ast.condition.Condition;
import uva.ql.ast.expression.Expression;
import uva.ql.ast.question.Question;
import uva.ql.ast.question.QuestionComputed;
import uva.ql.ast.question.QuestionVanilla;
import uva.ql.ast.value.Value;
import uva.ql.ast.variable.Variable;

public interface INodeVisitor {

	public void visitForm(Form form);
	public void visitBlock(Block block);
	public void visitExp(Expression expression);
	
	public void visitQuestion(Question question);
	public void visitQuestionComputed(QuestionComputed questionComputed);
	public void visitQuestionVanilla(QuestionVanilla questionVanilla);
	
	public void visitCondition(Condition condition);
	public void visitIfCondition(CondIfStatement condIfStatement);
	public void visitIfElseCondition(CondIfElseStatement condIfElseStatement);
	
	public void visitVar(Variable variable);
	public void visitVal(Value values);
}
