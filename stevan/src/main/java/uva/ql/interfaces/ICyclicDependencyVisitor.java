package uva.ql.interfaces;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.AbstractArithmeticOperator;
import uva.ql.ast.expressions.abstracts.AbstractLogicalOperator;
import uva.ql.ast.expressions.abstracts.AbstractRelationalOperator;
import uva.ql.ast.expressions.abstracts.AbstractSingleLogicalOperator;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.variables.VarGeneric;

public interface ICyclicDependencyVisitor {
	
	public void visitForm(Form form);
	public void visitBlock(Block block);
	
	public void visitQuestionVanilla(QuestionVanilla questionVanilla);
	public void visitQuestionComputed(QuestionComputed questionComputed);
	
	public void visitArithmeticOperator(AbstractArithmeticOperator exp);
	public void visitLogicalOperator(AbstractLogicalOperator exp);
	public void visitRelationalOperator(AbstractRelationalOperator exp);
	public void visitSingleLogicalOperator(AbstractSingleLogicalOperator exp);
	
	public void visitCondIfElseStatement(CondIfElseStatement condIfElseStatement);
	public void visitCondIfStatement(CondIfStatement condIfStatement);
	public void visitVarGeneric(VarGeneric var);
	
}
