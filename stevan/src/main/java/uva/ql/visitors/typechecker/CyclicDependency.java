package uva.ql.visitors.typechecker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.interfaces.ICyclicDependencyVisitor;

public class CyclicDependency implements ICyclicDependencyVisitor {

	private final Map<String, Variable> questionVariables = new HashMap<String, Variable>(0);
	private final Map<String, Variable> cyclicVariables = new HashMap<String, Variable>(0);
	
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

				//TODO: Create error Object
				System.out.println("error Cyclic: " + pair.getKey() + " - " + pair.getValue().getLine() + ", " + pair.getValue().getColumn());
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

	@Override
	public void visitVarGeneric(VarGeneric var) {
		cyclicVariables.put(var.getName(), var);
	}

}
