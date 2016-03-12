package uva.ql.visitors.typechecker;

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
import uva.ql.ast.variables.VarGeneric;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.visitors.typechecker.abstracts.AbstractTypeChecker;
import uva.ql.visitors.typechecker.abstracts.ErrorWarning;
import uva.ql.visitors.typechecker.errors.ErrorCyclic;

public class CyclicDependency extends AbstractTypeChecker implements ICyclicDependencyVisitor {

	private final Map<String, Variable> questionVariables = new HashMap<String, Variable>(0);
	private final Map<String, Variable> cyclicVariables = new HashMap<String, Variable>(0);
	private final List<ErrorWarning> errorMessages = new ArrayList<ErrorWarning>(0);
	
	@Override
	public void visitForm(Form form) {
		
		for(int i=0; i<form.size(); i++) {
			
			Block block = (Block) form.get(i);
			block.accept(this);
		}
		
		Iterator<Entry<String, Variable>> varIterator = cyclicVariables.entrySet().iterator();
		
		while(varIterator.hasNext()) {
			
			Entry<String, Variable> pair = varIterator.next();
			
			if(questionVariables.containsKey(pair.getKey())) {

				errorMessages.add(new ErrorCyclic(pair.getKey(), pair.getValue().getLine(), pair.getValue().getColumn()));
			}
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
		questionVariables.put(var.getName(), var);
	}
	
	@Override
	public void visitQuestionComputed(QuestionComputed questionComputed) {
		
		Variable var = questionComputed.getVariable();
		questionVariables.put(var.getName(), var);
		
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
	public void visitVarGeneric(VarGeneric var) {
		cyclicVariables.put(var.getName(), var);
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
