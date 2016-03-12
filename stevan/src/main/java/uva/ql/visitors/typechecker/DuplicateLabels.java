package uva.ql.visitors.typechecker;

import java.util.HashMap;
import java.util.Map;

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
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.visitors.typechecker.abstracts.AbstractTypeChecker;
import uva.ql.visitors.typechecker.errors.WarningDuplicateLabel;

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
	public void visitArithmeticOperator(AbstractArithmeticOperator exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitLogicalOperator(AbstractLogicalOperator exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitRelationalOperator(AbstractRelationalOperator exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitSingleLogicalOperator(AbstractSingleLogicalOperator exp) {
		exp.getLhs().accept(this);
	}
}
