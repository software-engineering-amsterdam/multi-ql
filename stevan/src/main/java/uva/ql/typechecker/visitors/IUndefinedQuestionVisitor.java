package uva.ql.typechecker.visitors;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.ArithmeticOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorUnary;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.variables.VarGeneric;

public interface IUndefinedQuestionVisitor {
	
	public void visitForm(Form form);
	public void visitBlock(Block block);
	
	public void visitQuestionVanilla(QuestionVanilla questionVanilla);
	public void visitQuestionComputed(QuestionComputed questionComputed);

	public void visitArithmeticOperator(ArithmeticOperatorBinary exp);
	public void visitLogicalOperator(LogicalOperatorBinary exp);
	public void visitRelationalOperator(RelationalOperatorBinary exp);
	public void visitSingleLogicalOperator(LogicalOperatorUnary exp);
	
	public void visitCondIfElseStatement(CondIfElseStatement condIfElseStatement);
	public void visitCondIfStatement(CondIfStatement condIfStatement);
	
	public void visitVarGeneric(VarGeneric varGeneric);
	
}
