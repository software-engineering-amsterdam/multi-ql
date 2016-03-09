package uva.ql.interfaces;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.conditionals.abstracts.Condition;
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
import uva.ql.ast.expressions.ExpParentheses;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.ast.values.ValueBool;
import uva.ql.ast.values.ValueDouble;
import uva.ql.ast.values.ValueInt;
import uva.ql.ast.variables.VarBool;
import uva.ql.ast.variables.VarDate;
import uva.ql.ast.variables.VarDecimal;
import uva.ql.ast.variables.VarDouble;
import uva.ql.ast.variables.VarGeneric;
import uva.ql.ast.variables.VarInt;
import uva.ql.ast.variables.VarMoney;
import uva.ql.ast.variables.VarStr;

public interface IArithmeticOperatorVisitor {

	public void visitForm(Form form);
	public void visitBlock(Block block);
	public void visitQuestion(Question question);
	public void visitQuestionVanilla(QuestionVanilla question);
	public void visitQuestionComputed(QuestionComputed question);
	
	public void visitCondition(Condition condition);
	public void visitCondIfElseStatement(CondIfElseStatement condition);
	public void visitCondIfStatement(CondIfStatement condition);
	
	public void visitExp(Expression exp);
	public void visitExpParentheses(ExpParentheses exp);
	public void visitExpAdd(ExpAdd exp);
	public void visitExpAnd(ExpAnd exp);
	public void visitExpDivide(ExpDivide exp);
	public void visitExpEqualTo(ExpEqualTo exp);
	public void visitExpGreaterThen(ExpGreaterThen exp);
	public void visitExpGreaterThenOrEqualTo(ExpGreaterThenOrEqualTo exp);
	public void visitExpLessThen(ExpLessThen exp);
	public void visitExpLessThenOrEqualTo(ExpLessThenOrEqualTo exp);
	public void visitExpMinus(ExpMinus exp);
	public void visitExpMultiply(ExpMultiply exp);
	public void visitExpNot(ExpNot exp);
	public void visitExpNotEqualTo(ExpNotEqualTo exp);
	public void visitExpOr(ExpOr exp);
	
	public void visitValueBool(ValueBool val);
	public void visitValueDouble(ValueDouble val);
	public void visitValueInt(ValueInt val);
	
	public void visitVarBool(VarBool var);
	public void visitVarDate(VarDate var);
	public void visitVarDecimal(VarDecimal var);
	public void visitVarDouble(VarDouble var);
	public void visitVarGeneric(VarGeneric var);
	public void visitVarInt(VarInt var);
	public void visitVarMoney(VarMoney var);
	public void visitVarStr(VarStr var);
	
}
