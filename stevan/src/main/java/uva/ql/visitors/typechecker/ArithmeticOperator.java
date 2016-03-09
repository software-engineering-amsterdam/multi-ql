package uva.ql.visitors.typechecker;

import java.util.ArrayList;
import java.util.List;

import uva.ql.ast.Block;
import uva.ql.ast.EnumType;
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
import uva.ql.interfaces.IArithmeticOperatorVisitor;

public class ArithmeticOperator implements IArithmeticOperatorVisitor {

	private static final List<EnumType> EXP_TYPES = new ArrayList<EnumType>(0);
	
	static{
		EXP_TYPES.add(EnumType.DECIMAL);
		EXP_TYPES.add(EnumType.DOUBLE);
		EXP_TYPES.add(EnumType.INTEGER);
		EXP_TYPES.add(EnumType.MONEY);
	}
	
	@Override
	public void visitForm(Form form) {
		
		for(int i=0; i<form.size(); i++) {
			
			Block block = (Block) form.get(i);
			block.accept(this);
		}
	}

	@Override
	public void visitBlock(Block block) {

		for(int i=0; i<block.size(); i++) {
			
			block.get(i).accept(this);
		}
	}

	@Override
	public void visitQuestion(Question question) {}

	@Override
	public void visitQuestionVanilla(QuestionVanilla questionVanilla) {}

	@Override
	public void visitQuestionComputed(QuestionComputed questionComputed) {
		System.out.println("QuestionComputed");
		questionComputed.getExp().accept(this);
	}

	@Override
	public void visitCondition(Condition condition) {}

	@Override
	public void visitCondIfStatement(CondIfStatement condIfStatement) {
		System.out.println("CondIfStatement");
		condIfStatement.getExpression().accept(this);
		condIfStatement.getLhs().accept(this);
	}
	
	@Override
	public void visitCondIfElseStatement(CondIfElseStatement condIfElseStatement) {
		System.out.println("CondIfElseStatement");
		condIfElseStatement.getExpression().accept(this);
		condIfElseStatement.getLhs().accept(this);
		condIfElseStatement.getRhs().accept(this);
	}

	@Override
	public void visitExp(Expression exp) {
		System.out.println("Exp");
	}
	
	@Override
	public void visitExpParentheses(ExpParentheses exp) {
		System.out.println("ExpParentheses");
	}

	@Override
	public void visitExpAdd(ExpAdd exp) {
		System.out.println("AddExp");
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}
	
	@Override
	public void visitExpMinus(ExpMinus exp) {
		System.out.println("MinusExp " + exp.eval());
		exp.eval();
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpMultiply(ExpMultiply exp) {
		System.out.println("MultiplyExp");
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}
	
	@Override
	public void visitExpDivide(ExpDivide exp) {
		System.out.println("DivideExp");
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}
	
	@Override
	public void visitValueDouble(ValueDouble val) {
		System.out.println("ValueDouble");
	}

	@Override
	public void visitValueInt(ValueInt val) {
		System.out.println("ValueInt");
	}
	
	@Override
	public void visitVarDecimal(VarDecimal var) {
		System.out.println("VarDecimal");
	}

	@Override
	public void visitVarDouble(VarDouble var) {
		System.out.println("VarDouble");
	}
	
	@Override
	public void visitVarInt(VarInt var) {
		System.out.println("VarInt");
	}

	@Override
	public void visitVarMoney(VarMoney var) {
		System.out.println(var.getName() + " - VarMoney - " + var.getType());
	}

	@Override
	public void visitVarStr(VarStr var) {
		System.out.println("VarString");
	}
	
	@Override
	public void visitVarGeneric(VarGeneric var) {
		System.out.println(var.getName() + " - VarGeneric - " + var.getType());
	}

	@Override
	public void visitExpAnd(ExpAnd exp) {}

	@Override
	public void visitExpEqualTo(ExpEqualTo exp) {}

	@Override
	public void visitExpGreaterThen(ExpGreaterThen exp) {}

	@Override
	public void visitExpGreaterThenOrEqualTo(
			ExpGreaterThenOrEqualTo exp) {}

	@Override
	public void visitExpLessThen(ExpLessThen exp) {}

	@Override
	public void visitExpLessThenOrEqualTo(
			ExpLessThenOrEqualTo exp) {}

	@Override
	public void visitExpNot(ExpNot exp) {}

	@Override
	public void visitExpNotEqualTo(ExpNotEqualTo exp) {}

	@Override
	public void visitExpOr(ExpOr exp) {}

	@Override
	public void visitValueBool(ValueBool val) {}

	@Override
	public void visitVarBool(VarBool var) {}

	@Override
	public void visitVarDate(VarDate var) {}
}
