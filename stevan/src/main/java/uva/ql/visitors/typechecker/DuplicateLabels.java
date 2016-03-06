package uva.ql.visitors.typechecker;

import java.util.HashMap;
import java.util.Map;

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
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.interfaces.IDupllicateLabelsVisitor;

public class DuplicateLabels implements IDupllicateLabelsVisitor {

	private final Map<String, Question> questions = new HashMap<String, Question>(0);
	
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
	public void visitQuestionVanilla(QuestionVanilla questionVanilla) {
		
		if (questions.containsKey(questionVanilla.getLabel())) {
			
			//TODO: Create error Object
			System.out.println("warning duplicate label: " + questionVanilla.getLabel() + " - " + questionVanilla.getLine() + ", " + questionVanilla.getColumn());
		}
		else {
			questions.put(questionVanilla.getLabel(), questionVanilla);
		}
		
	}
	
	@Override
	public void visitQuestionComputed(QuestionComputed questionComputed) {
		
		if (questions.containsKey(questionComputed.getLabel())) {
			
			//TODO: Create error Object
			System.out.println("warning duplicate label: " + questionComputed.getLabel() + " - " + questionComputed.getLine() + ", " + questionComputed.getColumn());
		}
		else {
			questions.put(questionComputed.getLabel(), questionComputed);
		}
	}
	
	@Override
	public void visitCondIfStatement(CondIfStatement condIfStatement) {
		condIfStatement.getExpression().accept(this);
		condIfStatement.getLhs().accept(this);
	}
	
	@Override
	public void visitCondIfElseStatement(CondIfElseStatement condIfElseStatement) {
		condIfElseStatement.getExpression().accept(this);
		condIfElseStatement.getLhs().accept(this);
		condIfElseStatement.getRhs().accept(this);
	}

	@Override
	public void visitExpAdd(ExpAdd exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpAnd(ExpAnd exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpDivide(ExpDivide exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpEqualTo(ExpEqualTo exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpGreaterThen(ExpGreaterThen exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpGreaterThenOrEqualTo(ExpGreaterThenOrEqualTo exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpLessThen(ExpLessThen exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpLessThenOrEqualTo(
			ExpLessThenOrEqualTo exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpMinus(ExpMinus exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpMultiply(ExpMultiply exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpNot(ExpNot exp) {
		exp.getLhs().accept(this);
	}

	@Override
	public void visitExpNotEqualTo(ExpNotEqualTo exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitExpOr(ExpOr exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}
}
