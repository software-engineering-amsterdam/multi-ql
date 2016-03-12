package uva.ql.typechecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.typechecker.abstracts.AbstractTypeChecker;
import uva.ql.typechecker.errors.ErrorDuplicateQuestion;
import uva.ql.visitors.interfaces.typechecker.IDupllicateQuestionDifferentTypesVisitor;

public class DuplicateQuestionDifferentTypes extends AbstractTypeChecker implements IDupllicateQuestionDifferentTypesVisitor {

	private final Map<String, List<Variable>> questionVariables = new HashMap<String, List<Variable>>(0);
	
	@Override
	public void visitForm(Form form) {
		
		for(int i=0; i<form.size(); i++) {
			
			Block block = (Block) form.get(i);
			block.accept(this);
		}
		
		Iterator<Entry<String, List<Variable>>> varIterator = questionVariables.entrySet().iterator();
		
		while(varIterator.hasNext()) {
			
			Entry<String, List<Variable>> variables = varIterator.next();
			
			if(variables.getValue().size() > 1) {

				checkForDuplication(variables.getValue());
			}
		}
	}
	
	private void checkForDuplication(List<Variable> variables) {
	
		Variable tempVar = variables.get(0);
		variables.remove(0);
		
		for(Variable var : variables) {

			if(!tempVar.getType().equals(var.getType())) {
				errorMessages.add(new ErrorDuplicateQuestion(var.getName(), var.getLine(), var.getColumn()));
			}
			
			tempVar = var;
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
		
		Variable var = questionVanilla.getVariable();
		
		if(questionVariables.containsKey(var.getName())) {
			List<Variable> variables = questionVariables.get(var.getName());
			variables.add(var);
			questionVariables.put(var.getName(), variables);
		}
		else {
			List<Variable> variables = new ArrayList<Variable>(0);
			variables.add(var);
			questionVariables.put(var.getName(), variables);
		}
	}
	
	@Override
	public void visitQuestionComputed(QuestionComputed questionComputed) {
		
		Variable var = questionComputed.getVariable();
		
		if(questionVariables.containsKey(var.getName())) {
			List<Variable> variables = questionVariables.get(var.getName());
			variables.add(var);
			questionVariables.put(var.getName(), variables);
		}
		else {
			List<Variable> variables = new ArrayList<Variable>(0);
			variables.add(var);
			questionVariables.put(var.getName(), variables);
		}
		
		questionComputed.getExp().accept(this);
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
