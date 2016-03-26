package uva.ql.typechecker;

import java.util.HashMap;
import java.util.Map;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.ArithmeticOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorUnary;
import uva.ql.ast.questions.Question;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.typechecker.abstracts.AbstractTypeChecker;
import uva.ql.typechecker.errors.WarningDuplicateLabel;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;

public class DuplicateLabels extends AbstractTypeChecker implements IDupllicateLabelsVisitor {

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
			errorMessages.add(new WarningDuplicateLabel(questionVanilla.getLabel(), questionVanilla.getLine(), questionVanilla.getColumn()));
		}
		else {
			questions.put(questionVanilla.getLabel(), questionVanilla);
		}
		
	}
	
	@Override
	public void visitQuestionComputed(QuestionComputed questionComputed) {
		
		if (questions.containsKey(questionComputed.getLabel())) {
			errorMessages.add(new WarningDuplicateLabel(questionComputed.getLabel(), questionComputed.getLine(), questionComputed.getColumn()));
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
	public void visitArithmeticOperator(ArithmeticOperatorBinary exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitLogicalOperator(LogicalOperatorBinary exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitRelationalOperator(RelationalOperatorBinary exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitSingleLogicalOperator(LogicalOperatorUnary exp) {
		exp.getLhs().accept(this);
	}
}
