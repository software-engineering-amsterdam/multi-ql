package uva.ql.interfaces;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.ExpAdd;
import uva.ql.ast.expressions.ExpAnd;
import uva.ql.ast.expressions.ExpDivide;
import uva.ql.ast.expressions.ExpEqualTo;
import uva.ql.ast.expressions.ExpGreaterThen;
import uva.ql.ast.expressions.ExpGreaterThenOrEqualTo;
import uva.ql.ast.expressions.ExpLessThen;
import uva.ql.ast.expressions.ExpLessThenOrEqualTo;
import uva.ql.ast.expressions.ExpMinus;
import uva.ql.ast.expressions.ExpMultiply;
import uva.ql.ast.expressions.ExpNot;
import uva.ql.ast.expressions.ExpNotEqualTo;
import uva.ql.ast.expressions.ExpOr;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.variables.VarGeneric;

public interface IUndefinedQuestionVisitor {
	
	public void visitForm(Form form);
	public void visitBlock(Block block);
	
	public void visitQuestionVanilla(QuestionVanilla questionVanilla);
	public void visitQuestionComputed(QuestionComputed questionComputed);
	
	public void visitExpAdd(ExpAdd exp);
	public void visitExpAnd(ExpAnd expAnd);
	public void visitExpDivide(ExpDivide expDivide);
	public void visitExpEqualTo(ExpEqualTo expEqualTo);
	public void visitExpGreaterThen(ExpGreaterThen expGreaterThen);
	public void visitExpGreaterThenOrEqualTo(ExpGreaterThenOrEqualTo expGreaterThenOrEqualTo);
	public void visitExpLessThen(ExpLessThen expLessThen);
	public void visitExpLessThenOrEqualTo(ExpLessThenOrEqualTo expLessThenOrEqualTo);
	public void visitExpMinus(ExpMinus expMinus);
	public void visitExpMultiply(ExpMultiply expMultiply);
	public void visitExpNot(ExpNot expNot);
	public void visitExpNotEqualTo(ExpNotEqualTo expNotEqualTo);
	public void visitExpOr(ExpOr expOr);
	
	public void visitCondIfElseStatement(CondIfElseStatement condIfElseStatement);
	public void visitCondIfStatement(CondIfStatement condIfStatement);
	
	public void visitVarGeneric(VarGeneric varGeneric);
	
}
