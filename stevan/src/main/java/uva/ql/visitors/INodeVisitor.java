package uva.ql.visitors;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.conditionals.Condition;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.questions.Question;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.values.Value;
import uva.ql.ast.variables.Variable;

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
	public void visitVal(Value<?> values);
}
